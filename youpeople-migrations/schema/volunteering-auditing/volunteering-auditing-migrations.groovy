databaseChangeLog {
	changeSet(id: 't_volunteering_aud', author: 'mudryi_roman') {
		createTable(tableName: 't_volunteering_aud') {
			column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
			column(name: 'rev', type: 'int4') { constraints(nullable: false) }
			column(name: 'revtype', type: 'smallint')
			column(name: 'person_id', type: 'BIGINT') { constraints(nullable: false) }
			column(name: 'organization_id', type: 'BIGINT') { constraints(nullable: false) }
			column(name: 'cause', type: 'VARCHAR(255)')
			column(name: 'start_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
			column(name: 'end_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
			column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
			column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
			column(name: 'last_modified_by', type: 'VARCHAR(255)')
			column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
		}
		addPrimaryKey(columnNames: 'id,rev', constraintName: 'pk_volunteering_aud', tableName: 't_volunteering_aud')
	}
}