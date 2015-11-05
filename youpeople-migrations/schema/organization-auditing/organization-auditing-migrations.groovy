databaseChangeLog {
    changeSet(id: 't_organization_aud', author: 'mudryi_roman') {
        createTable(tableName: 't_organization_aud') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'rev', type: 'int4') { constraints(nullable: false) }
            column(name: 'revtype', type: 'smallint')
            column(name: 'name', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'picture', type: 'VARCHAR(255)')
            column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
            column(name: 'last_modified_by', type: 'VARCHAR(255)')
            column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
        addPrimaryKey(columnNames: 'id,rev', constraintName: 'pk_organization_aud', tableName: 't_organization_aud')
    }
}