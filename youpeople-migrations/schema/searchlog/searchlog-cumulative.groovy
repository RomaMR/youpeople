databaseChangeLog {
    include(file: 'youpeople-migrations/schema/searchlog/searchlog-migrations.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/searchlog/10-addprimarykey-searchlog.groovy', relativeToChangelogFile: false)
    include(file: 'youpeople-migrations/schema/searchlog/20-renametable-search-to-searchlog.groovy', relativeToChangelogFile: false)
}