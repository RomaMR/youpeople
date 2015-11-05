databaseChangeLog {
    changeSet(id: 'adding_view_for_industry_group_statistic', author: 'mudryi_roman') {
        createView(viewName: 'industry_group_statistic', replaceIfExists: true) {
            "SELECT t_industry_group.id, t_industry_group.name as name, COUNT(t_linkedin_person.id) as count FROM t_linkedin_person " +
                    "INNER JOIN t_industry_industry_group on (t_linkedin_person.industry_id = t_industry_industry_group.industry_id) " +
                    "INNER JOIN t_industry_group on (t_industry_industry_group.industry_group_id = t_industry_group.id) " +
                    "GROUP BY t_industry_group.id ORDER BY count DESC"
        }
    }
}