databaseChangeLog {
    changeSet(id: 't_industry_industry_group', author: 'mudryi_roman') {
        createTable(tableName: 't_industry_industry_group') {
            column(name: 'id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'industry_id', type: 'BIGINT')
            column(name: 'industry_group_id', type: 'BIGINT')
            column(name: 'main', type: 'BIT')
            column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
            column(name: 'last_modified_by', type: 'VARCHAR(255)')
            column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
        addPrimaryKey(columnNames: 'id', constraintName: 'pk_industry_industry_group', tableName: 't_industry_industry_group')
        addForeignKeyConstraint(baseColumnNames: 'industry_id', baseTableName: 't_industry_industry_group', constraintName:
                'fk_industry_industry_group_industry',  deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION',
                referencedColumnNames: 'id', referencedTableName: 't_industry')
        addForeignKeyConstraint(baseColumnNames: 'industry_group_id', baseTableName: 't_industry_industry_group', constraintName:
                'fk_industry_industry_group_industry_group', deferrable: false, initiallyDeferred: false, onDelete: 'NO ACTION', onUpdate: 'NO ACTION'
                , referencedColumnNames: 'id', referencedTableName: 't_industry_group')
    }
}