'use strict';

var gulp = require('gulp'),   
    sass = require('gulp-sass'),
    concat = require('gulp-concat');




gulp.task('material:build',function(){
    gulp.src(['vendor/materialize-src/sass/materialize.scss','vendor/materialize-src/sass/icon.css'])
        .pipe(sass())
        .pipe(concat('materialize.css'))
        .pipe(gulp.dest('vendor/materialize/css'));
});
