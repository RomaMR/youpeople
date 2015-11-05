package com.optigra.youpeople.web.commons;

/**
 * Created by romanmudryi on 10.10.15.
 */
public interface RMPath {

    interface Documentation {
        String ROOT = "/api-docs/**";
    }

    interface User {
        String ROOT = "/api/users";
        String SESSION = "/session";
        String REGISTRATION = "/registration";
    }

    interface Person {
        String ROOT = "/api/persons";
        String SEARCH = "/search";
    }

    interface SearchLog {
        String ROOT = "/api/search";
    }

    interface Statistic {
        String ROOT = "/api/statistic";
    }

    interface Organization {
        String ROOT = "/api/organizations";
        String NAMES = "/names";
    }
    interface Job {
        String ROOT = "/api/jobs";
    }

    interface Facebook {
        String ROOT = "/api/facebook";
        String LOGIN = "/login";
        String CALLBACK = "/callback";
    }

    interface FacebookPageMetric {
        String ROOT = "/api/facebook/pagemetrics";
    }
}
