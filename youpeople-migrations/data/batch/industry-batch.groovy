databaseChangeLog {
    changeSet(id: 'load-industry-data', author: 'mudryi_roman') {
        loadData(encoding: 'UTF-8', file: 'youpeople-migrations/data/industry.csv', separator: ';', tableName: 't_industry') {}
        loadData(encoding: 'UTF-8', file: 'youpeople-migrations/data/industry-group.csv', separator: ';', tableName: 't_industry_group') {}
        loadData(encoding: 'UTF-8', file: 'youpeople-migrations/data/industry-industry-group.csv', separator: ';', tableName:
                't_industry_industry_group') {}
    }
}