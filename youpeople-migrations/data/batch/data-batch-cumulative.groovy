databaseChangeLog {
    include(file: 'youpeople-migrations/data/batch/user-batch.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/data/batch/industry-batch.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/data/batch/organization-batch.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/data/batch/user-organization-batch.groovy', relativeToChangelogFile: false)
}