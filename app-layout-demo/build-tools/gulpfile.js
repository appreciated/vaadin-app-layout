'use strict';

var gulp = require('gulp');
var eslint = require('gulp-eslint');
var htmlExtract = require('gulp-html-extract');
var stylelint = require('gulp-stylelint');
var clean = require('gulp-clean');

gulp.task('lint', ['lint:js', 'lint:html', 'lint:css']);

gulp.task('lint:js', function() {
  return gulp.src([
    '../src/main/webapp/VAADIN/frontend/*.js',
    '../src/main/webapp/VAADIN/frontend/src/**/*.js',
    '../src/main/webapp/VAADIN/frontend/test/**/*.js'
  ])
    .pipe(eslint({fix: true }))
    .pipe(eslint.format())
    .pipe(eslint.failAfterError('fail'));
});

gulp.task('lint:html', function() {
  return gulp.src([
    '../src/main/webapp/VAADIN/frontend/*.html',
    '../src/main/webapp/VAADIN/frontend/src/**/*.html',
    '../src/main/webapp/VAADIN/frontend/test/**/*.html'
  ])
    .pipe(htmlExtract({
      sel: 'script, code-example code',
      strip: true
    }))
    .pipe(eslint({fix: true }))
    .pipe(eslint.format())
    .pipe(eslint.failAfterError('fail'));
});

gulp.task('lint:css', function() {
  return gulp.src([
    '../src/main/webapp/VAADIN/frontend/*.html',
    '../src/main/webapp/VAADIN/frontend/src/**/*.html',
    '../src/main/webapp/VAADIN/frontend/test/**/*.html'
  ])
    .pipe(htmlExtract({
      sel: 'style'
    }))
    .pipe(stylelint({
      reporters: [
        {formatter: 'string', console: true}
      ]
    }));
});


var filesToMove = [
    '../src/main/webapp/VAADIN/frontend/build/**/*',
    '!../src/main/webapp/VAADIN/frontend/build/polymer.json'
];

gulp.task('move', function(){
    gulp.src(filesToMove)
        .pipe(gulp.dest('../src/main/webapp/VAADIN/frontend'));
});

var filesToCleanUp = [
    '../src/main/webapp/VAADIN/frontend/es5/bower.json',
    '../src/main/webapp/VAADIN/frontend/es6/bower.json',
    '../src/main/webapp/VAADIN/frontend/build'
];

gulp.task('clean', function(){
    return gulp.src(filesToCleanUp, {read:false})
        .pipe(clean({force: true}));
});