databaseChangeLog {
	changeSet(id: '20150814-3', author: 'odisseus') {
		createTable(tableName: 't_volunteering') {
			column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
			column(name: 'person_id', type: 'BIGINT') { constraints(nullable: false) }
			column(name: 'organization_id', type: 'BIGINT') { constraints(nullable: false) }
			column(name: 'cause', type: 'VARCHAR(255)')
			column(name: 'start_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
			column(name: 'end_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
		}
	}
}