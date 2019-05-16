package org.csrf.synctoks.model;

import java.util.function.Function;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

public class Essential {
    //cookie which carries the session_ID ceated upon new session creation
	public static final Function<HttpSession, Cookie> COOKIE_WITH_SESSION_ID = session -> new Cookie("session_ID", session.getId());

}
