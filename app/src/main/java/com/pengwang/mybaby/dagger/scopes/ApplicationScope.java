package com.pengwang.mybaby.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Peng on 2/19/2017.
 * Application scope
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}
