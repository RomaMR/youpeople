databaseChangeLog {
    changeSet(id: 'adding_procedure_for_industry_by_group_statistic', author: 'mudryi_roman') {
        createProcedure() {
            "CREATE OR REPLACE FUNCTION industry_by_group_statistic (Parameter1 bigint)\n" +
                    "RETURNS TABLE(id bigint, name character varying, count bigint)\n" +
                    "AS \$BODY\$ SELECT t_industry.id, t_industry.name as name, COUNT(t_linkedin_person.id) as count FROM t_linkedin_person \n" +
                    "INNER JOIN t_industry on (t_linkedin_person.industry_id = t_industry.id)\n" +
                    "INNER JOIN t_industry_industry_group on (t_linkedin_person.industry_id = t_industry_industry_group.industry_id)\n" +
                    "INNER JOIN t_industry_group on (t_industry_industry_group.industry_group_id = t_industry_group.id) where t_industry_group.id = Parameter1\n" +
                    "GROUP BY t_industry.id  ORDER BY count DESC; \$BODY\$ LANGUAGE sql VOLATILE COST 300;\n" +
                    "ALTER FUNCTION industry_by_group_statistic(bigint)\n" +
                    "  OWNER TO postgres;"
        }
    }
}