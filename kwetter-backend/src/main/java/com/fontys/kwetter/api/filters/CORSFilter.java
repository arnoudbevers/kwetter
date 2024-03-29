package com.fontys.kwetter.api.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet Filter implementation class CORSFilter
 * https://howtodoinjava.com/servlets/java-cors-filter-example/
 */
// Enable it for Servlet 3.x implementations
/* @ WebFilter(asyncSupported = true, urlPatterns = { "/*" }) */
public class CORSFilter implements Filter {

  /**
   * Default constructor.
   */
  public CORSFilter() { }

  /**
   * @see Filter#destroy()
   */
  public void destroy() {}

  /**
   * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
   */
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
          throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;
    System.out.println("Filtering HTTP request of method " + request.getMethod());

    // Authorize (allow) all domains to consume the content
    ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
    ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
    ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers","*");
    ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Credentials","true");

    HttpServletResponse resp = (HttpServletResponse) servletResponse;

    // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
    if (request.getMethod().equals("OPTIONS")) {
      resp.setStatus(HttpServletResponse.SC_ACCEPTED);
      return;
    }

    // pass the request along the filter chain
    chain.doFilter(request, servletResponse);
  }

  /**
   * @see Filter#init(FilterConfig)
   */
  public void init(FilterConfig fConfig) throws ServletException { }

}