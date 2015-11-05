databaseChangeLog {
    changeSet(id: '1438783344964-15', author: 'mudryi_roman') {
        comment('Renaming of table search to searchlog.')
        renameTable(newTableName: 't_search_log', oldTableName: 't_search')
    }
}