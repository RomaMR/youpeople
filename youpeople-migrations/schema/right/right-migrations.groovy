databaseChangeLog {
    changeSet(id: '1438783344964-14', author: 'odisseus') {
        comment('Added t_right and t_user_right')

        createTable(tableName: 't_right') {
            column(name: 'name', type: 'VARCHAR(50)') { constraints(nullable: false) }
        }
        addPrimaryKey(columnNames: 'name', constraintName: 'pk_right', tableName: 't_right')

        createTable(tableName: 't_user_right') {
            column(name: 'user_id', type: 'BIGINT') { constraints(nullable: false) }
            column(name: 'right_name', type: 'VARCHAR(50)') { constraints(nullable: false) }
        }
        addPrimaryKey(columnNames: 'user_id, right_name', constraintName: 'pk_user_right', tableName: 't_user_right')

        addPrimaryKey(columnNames: 'id', constraintName: 'pk_user', tableName: 't_user')
        addForeignKeyConstraint(baseColumnNames: 'user_id', baseTableName: 't_user_right',
                constraintName: 'fk_user_right_user', deferrable: false, initiallyDeferred: false,
                onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'id',
                referencedTableName: 't_user')

        addForeignKeyConstraint(baseColumnNames: 'right_name', baseTableName: 't_user_right',
                constraintName: 'fk_user_right_right', deferrable: false, initiallyDeferred: false,
                onDelete: 'NO ACTION', onUpdate: 'NO ACTION', referencedColumnNames: 'name',
                referencedTableName: 't_right')
    }
}