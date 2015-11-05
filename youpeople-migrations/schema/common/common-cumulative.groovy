databaseChangeLog {
    include(file: 'youpeople-migrations/schema/common/10-addcolumn-for-envers-changes-auditing.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/common/20-createsequnce.groovy', relativeToChangelogFile: false)
}