package com.ys.core.service;

import com.ys.core.infra.domain.ep.*;
import com.ys.core.infra.repository.ep.*;
import com.ys.eportal.model.ProjectStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rob on 4/4/15.
 */

@Service
@Transactional
public class PortalService extends ServicesBase {

    private static Logger logger = LoggerFactory.getLogger(PortalService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectNotesRepository projectNotesRepository;

    @Autowired
    private ProjectActivityRepository projectActivityRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ProjectResourceRepository projectResourceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ProjectStats retrieveProjectStatus() {


        List<Object[]> list = this.salesOrderRepository.retrieveOrderStats();

        ProjectStats proj = new ProjectStats();
        // @TODO improve the ugliness
        if (list != null) {

            for (Object sos[] : list) {
                try {

                    // need to deal with the nordefinedstatus
                    if (sos[0] == null || sos[0].equals(Constants.Projects.STATUS_NOTDEFINED)) {
                        proj.setNumNotdefinedStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.Projects.STATUS_BOOKED)) {
                        proj.setNumBookedStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.Projects.STATUS_COMPLETE)) {
                        proj.setNumCompleteStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.Projects.STATUS_INPROCESS)) {
                        proj.setNumInprocessStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.Projects.STATUS_POSTSUPPORT)) {
                        proj.setNumPostSupportStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.Projects.STATUS_PROPOSED)) {
                        proj.setNumProposedStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.Projects.STATUS_RANDSUPPORT)) {
                        proj.setNumRandSupportStatus(((BigInteger) sos[1]).intValue());
                    } else if (sos[0].equals(Constants.Projects.STATUS_SCHEDULED)) {
                        proj.setNumScheduledStatus(((BigInteger) sos[1]).intValue());
                    }
                } catch (ClassCastException e) {
                    // ignore. rewrite
                }
            }

        }

        return proj;
    }

    public Iterable<CustomerEntity> find(CustomerSearchSupport search) {
        List<CustomerEntity> results = null;

        if (search.getCustomerId() > 0) {
            CustomerEntity c = this.findCustomerByID(search.getCustomerId());
            if (c != null) {
                results = new ArrayList<CustomerEntity>();
                results.add(c);
            }
        } else if (search.getName() != null && !search.getName().trim().equals("")) {
            results = this.searchByCustomerName(search.getName());
        } else {
            results = this.findAllCustomers();
        }

        return results;
    }

    public CustomerEntity findCustomerByID(long customerId) {

        return this.customerRepository.findOne(customerId);
    }

    public ResourceEntity findResourceEntityById(long resourceId) {
        return this.resourceRepository.findOne(resourceId);
    }

    public ProjectResourceEntity findProjectResourceEntityById(long resourceId) {
        return this.projectResourceRepository.findOne(resourceId);
    }

    public CustomerEntity findCustomerByName(String name) {
        CustomerEntity customer = null;
        List<CustomerEntity> list = this.customerRepository.findByName(name);
        if (list != null && list.size() > 0) {
            customer = list.get(0);
        }
        return customer;
    }

    public List<CustomerEntity> searchByCustomerName(String name) {

        return this.customerRepository.findByNameLike(this.processWildCards(name));

    }

    public ProjectActivityEntity findProjectActivityById(long activityId) {
        return this.projectActivityRepository.findOne(activityId);
    }

    public List<CustomerEntity> findAllCustomers() {
        return (List) this.customerRepository.findAll();
    }


    public CustomerEntity saveCustomer(CustomerEntity customer) {
        return this.customerRepository.save(customer);
    }


    public void save(SalesOrderEntity salesOrderEntity) {
        this.salesOrderRepository.save(salesOrderEntity);
    }

    public void save(ProjectActivityEntity projectActivity) {
        this.projectActivityRepository.save(projectActivity);
        //entityManager.refresh(projectActivity);
    }

    public void save(ProjectEntity projectEntity) {
        this.projectRepository.save(projectEntity);
       // entityManager.refresh(projectEntity);
    }

    public void addNote(ProjectNotesEntity pne) {
        this.projectNotesRepository.save(pne);

    }

    public void delete(ProjectNotesEntity projectNotesEntity) {
        this.projectNotesRepository.deleteProjectNotesEntity(projectNotesEntity.getNoteId());
        ProjectEntity pe = projectNotesEntity.getProject();
        pe.getNotes().remove(projectNotesEntity);
        this.projectRepository.save(pe);
    }


    public SalesOrderEntity findSalesOrderEntityById(long salesOrderId) {
        return this.salesOrderRepository.findOne(salesOrderId);

    }

    public void save(ResourceEntity resourceEntity) {
        this.resourceRepository.save(resourceEntity);
    }

    public void delete(ResourceEntity resourceEntity) {
        this.resourceRepository.delete(resourceEntity);
    }


    public void delete(ProjectResourceEntity projectResourceEntity) {


        this.projectResourceRepository.deleteProjectResourceEntity(projectResourceEntity.getProjectResourceId());
        ProjectEntity pe = projectResourceEntity.getProject();
        pe.getProjectResources().remove(projectResourceEntity);
        ResourceEntity re = projectResourceEntity.getResource();
        re.getProjectResources().remove(projectResourceEntity);
        this.projectRepository.save(pe);
        this.resourceRepository.save(re);


    }

    public void save(ProjectResourceEntity projectResourceEntity) {

        this.projectResourceRepository.save(projectResourceEntity);

        ProjectEntity pe = projectResourceEntity.getProject();
        pe.getProjectResources().add(projectResourceEntity);
        ResourceEntity re = projectResourceEntity.getResource();
        re.getProjectResources().add(projectResourceEntity);
        this.projectRepository.save(pe);
        this.resourceRepository.save(re);

    }

    public SalesOrderEntity findSalesOrderEntityByNumber(String salesOrderNumber) {
        List<SalesOrderEntity> list = this.salesOrderRepository.findBySalesOrderNumber(salesOrderNumber);
        SalesOrderEntity soe = null;
        if (list != null && list.size() > 0) {
            soe = list.get(0);
        }
        return soe;
    }

    public ProjectEntity findProjectBySalesOrderNumber(String salesOrderNumber) {

        ProjectEntity pe = null;

        List<ProjectEntity> peList = this.projectRepository.findBySalesOrdersSalesOrderNumber(salesOrderNumber);


        if (peList != null && peList.size() > 0) {
            pe = peList.get(0);
        }
        return pe;
    }

    public ProjectNotesEntity findProjectNoteById(long projectNoteId) {

        ProjectNotesEntity pne = null;

        pne = this.projectNotesRepository.findOne(projectNoteId);

        return pne;
    }


    public List<ProjectEntity> findByImportControlId(long importControlId) {
        List<ProjectEntity> list = this.projectRepository.findBySalesOrdersImportControlId(importControlId);
        return list;
    }

    /* public Set<SalesOrderEntity> findBySalesOrderCustomerIdlId(long customerId) {
         return null;// this.salesOrderRepository.findByCustomerCustomerId(customerId);

     }
 */
    public List<ProjectEntity> findByStatus(String status) {
        return this.projectRepository.findByStatus(status);
    }

    public List<SalesOrderEntity> findAllSalesOrders() {
        return (List) this.salesOrderRepository.findAll();
    }


    public List<ProjectEntity> findAllProjects() {
        return (List) this.projectRepository.findAll();
    }


    public List<UMProjectSearchResults> findAllProjectsSearchResults() {

        String theQuery="SELECT p.project_Id, e.sales_order_number,e.sales_order_id,e.model_group,c.customer_id,c.name, p.status, p.health,a.date  as bookedDate\n" +
                "from ep_customer c, ep_project p, ep_sales_order e, ep_project_activity a\n" +
                " where p.project_id=e.project_id AND e.customer_id = c.customer_id AND a.project_id = p.project_id AND a.name ='bookDate'\n";

        Query q = this.entityManager.createNativeQuery(theQuery, "ProjectSearchResult");

        return q.getResultList();


    }

    public ProjectEntity findProjectByProjectId(long projectId) {
        return this.projectRepository.findOne(projectId);
    }


    public ProjectResourceEntity findProjectResourceById(long id) {
        return this.projectResourceRepository.findOne(id);
    }

    public Iterable<ProjectEntity> find(ProjectSearchSupport search) {
        List<ProjectEntity> results = null;

        if (search.getSalesOrderNumber() != null && !search.getSalesOrderNumber().trim().equals("")) {
            ProjectEntity pe = this.findProjectBySalesOrderNumber(search.getSalesOrderNumber());
            if (pe != null) {
                results = new ArrayList<ProjectEntity>();
                results.add(pe);
            }
        } else if (search.getCustomerName() != null && !search.getCustomerName().trim().equals("")) {
            results = this.projectRepository.findByNameLike(this.processWildCards(search.getCustomerName()));

        } else if (search.getImportControlId() > 0) {
            results = this.findByImportControlId(search.getImportControlId());

        } else if (search.getStatus() != null && !search.getStatus().trim().equals("")) {
            results = this.findByStatus(search.getStatus());
        } else if (search.getModelGroup() != null && !search.getModelGroup().trim().equals("")) {
            results = this.projectRepository.findBySalesOrdersModelGroup(search.getModelGroup());
        } else {
            results = this.findAllProjects();
        }

        return results;
    }


    public Iterable<ResourceEntity> find(ResourceSearchSupport search) {
        Iterable<ResourceEntity> results = null;

        results = this.findAllResources();


        return results;
    }

    public Iterable<ResourceEntity> findAllResources() {
        return this.resourceRepository.findAll();
    }

}