databaseChangeLog {
    changeSet(id: 'load-user-data', author: 'mudryi_roman') {
        loadData(encoding: 'UTF-8', file: 'youpeople-migrations/data/user.csv', separator: ';', tableName: 't_user'){}
    }
}