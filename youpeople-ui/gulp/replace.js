'use strict';

var path = require('path');
var gulp = require('gulp');
var conf = require('./conf');

var replace = require('gulp-replace-task');

var $ = require('gulp-load-plugins')();

gulp.task('replace', function () {
    gulp.src(path.join(conf.paths.src, '**/*.js'))
        .pipe(replace({
            patterns: [
                {
                    match: 'canonicalUrl',
                    replacement: conf.canonicalUrl.dev + conf.baseUrl.dev
                },
                {
                    match: 'baseUrl',
                    replacement: conf.baseUrl.dev
                }
            ]
        }))
        .pipe(gulp.dest(path.join(conf.paths.tmp, '/serve')));

});
