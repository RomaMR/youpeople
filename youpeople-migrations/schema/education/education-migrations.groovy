databaseChangeLog {
	changeSet(id: '20150814-1', author: 'odisseus') {
		createTable(tableName: 't_education') {
			column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
			column(name: 'person_id', type: 'BIGINT') { constraints(nullable: false) }
			column(name: 'organization_id', type: 'BIGINT') { constraints(nullable: false) }
			column(name: 'degree', type: 'VARCHAR(255)')
			column(name: 'field_of_study', type: 'VARCHAR(255)')
			column(name: 'start_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
			column(name: 'end_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
		}
	}
}