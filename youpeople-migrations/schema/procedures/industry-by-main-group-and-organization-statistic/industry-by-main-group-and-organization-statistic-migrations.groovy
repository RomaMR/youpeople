databaseChangeLog {
    changeSet(id: 'adding_procedure_for_industry_by_main_group_and_organization_statistic', author: 'mudryi_roman') {
        createProcedure() {
            "CREATE OR REPLACE FUNCTION industry_by_main_group_and_organization_statistic (Parameter1 bigint, Parameter2 bigint)\n" +
                    "RETURNS \n" +
                    "TABLE(id bigint, name character varying, count bigint)\n" +
                    "AS\n" +
                    "\$BODY\$\n" +
                    "SELECT t_industry.id, t_industry.name as name, COUNT(t_linkedin_person.id) as count FROM t_linkedin_person \n" +
                    "INNER JOIN t_industry on (t_linkedin_person.industry_id = t_industry.id)\n" +
                    "INNER JOIN t_industry_industry_group on (t_linkedin_person.industry_id = t_industry_industry_group.industry_id)\n" +
                    "INNER JOIN t_industry_group on (t_industry_industry_group.industry_group_id = t_industry_group.id) \n" +
                    "where (t_industry_group.id = Parameter1) AND (t_industry_industry_group.main = TRUE) AND (\n" +
                    "EXISTS (SELECT 1 FROM t_job WHERE (t_job.organization_id = Parameter2) AND (t_job.person_id = t_linkedin_person.id)) OR\n" +
                    "EXISTS (SELECT 1 FROM t_education WHERE (t_education.organization_id = Parameter2) AND (t_education.person_id = t_linkedin_person.id)) OR\n" +
                    "EXISTS (SELECT 1 FROM t_volunteering WHERE (t_volunteering.organization_id = Parameter2) AND (t_volunteering.person_id = t_linkedin_person.id)) \n" +
                    ") GROUP BY t_industry.id ORDER BY count DESC;\n" +
                    "\$BODY\$\n" +
                    "LANGUAGE sql VOLATILE\n" +
                    "COST 300;\n" +
                    "ALTER FUNCTION industry_by_main_group_and_organization_statistic(bigint, bigint)\n" +
                    "OWNER TO postgres;"
        }
    }
}