databaseChangeLog {
    changeSet(id: 'load-user-organization-data', author: 'mudryi_roman') {
        loadData(encoding: 'UTF-8', file: 'youpeople-migrations/data/user-organization.csv', separator: ';', tableName: 't_user_organization'){}
    }
}