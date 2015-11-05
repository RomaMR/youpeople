databaseChangeLog {
    changeSet(id: 'adding_procedure_for_industry_main_group_by_organization_statistic', author: 'mudryi_roman') {
        createProcedure() {
            "CREATE OR REPLACE FUNCTION industry_main_group_by_organization_statistic (Parameter1 bigint)\n" +
                    "RETURNS \n" +
                    "TABLE(id bigint, name character varying, count bigint)\n" +
                    "AS\n" +
                    "\$BODY\$\n" +
                    "SELECT t_industry_group.id, t_industry_group.name, count(t_linkedin_person.id) AS count FROM t_linkedin_person\n" +
                    "INNER JOIN t_industry_industry_group ON t_linkedin_person.industry_id = t_industry_industry_group.industry_id\n" +
                    "INNER JOIN t_industry_group ON t_industry_industry_group.industry_group_id = t_industry_group.id\n" +
                    "WHERE (t_industry_industry_group.main = TRUE) AND (\n" +
                    "EXISTS (SELECT 1 FROM t_job WHERE (t_job.organization_id = Parameter1) AND (t_job.person_id = t_linkedin_person.id)) OR\n" +
                    "EXISTS (SELECT 1 FROM t_education WHERE (t_education.organization_id = Parameter1) AND (t_education.person_id = t_linkedin_person.id)) OR\n" +
                    "EXISTS (SELECT 1 FROM t_volunteering WHERE (t_volunteering.organization_id = Parameter1) AND (t_volunteering.person_id = t_linkedin_person.id))\n" +
                    ")\n" +
                    "  GROUP BY t_industry_group.id\n" +
                    "  ORDER BY count(t_linkedin_person.id) DESC;\n" +
                    "\$BODY\$\n" +
                    "LANGUAGE sql VOLATILE\n" +
                    "COST 300;\n" +
                    "ALTER FUNCTION industry_main_group_by_organization_statistic(bigint)\n" +
                    "OWNER TO postgres;"
        }
    }
}