package com.ys.eportal.util;

import com.ys.eportal.model.ImportOracleObi;
import org.springframework.batch.item.ItemProcessor;

public class OracleOBISalesOrderProcessor implements ItemProcessor<ImportOracleObi, ImportOracleObi> {

    @Override
    public ImportOracleObi process(final ImportOracleObi person) throws Exception {
            /*final String firstName = person.getFirstName().toUpperCase();
            final String lastName = person.getLastName().toUpperCase();

            final Person transformedPerson = new Person(firstName, lastName);

            System.out.println("Converting (" + person + ") into (" + transformedPerson + ")");

            return transformedPerson;
            */

        // noop for now
        return person;
    }

}

