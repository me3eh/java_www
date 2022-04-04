package com.example.filtry;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter(filterName = "/", urlPatterns = "/*")
public class FilterSom implements Filter {
    public HashMap<String, Link> links;
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String link = ((HttpServletRequest) request).getRequestURL().toString();
        HttpSession session = ((HttpServletRequest) request).getSession();
        links = (HashMap<String, Link>) session.getAttribute("links");
        if( links == null ) {
            Link new_link = new Link(link);
            links = new HashMap<>();
            links.put( link, new_link );
        }
        else if( !links.containsKey(link) ){
            Link new_link = new Link(link);
            links.put( link, new_link );
        }
        else{
            Link got_link = links.get(link);
            got_link.addVisit();
            links.put(link, got_link);
        }
        ArrayList<Link> sortedLinks = new ArrayList<>(links.values());
        Collections.sort(sortedLinks, new Sortbyroll() );
        session.setAttribute("sortedLinks", sortedLinks);
        session.setAttribute("links", links);
        chain.doFilter(request, response);
    }
    class Sortbyroll implements Comparator<Link>
    {
        public int compare(Link a, Link b)
        {
            return b.getTimesVisited() - a.getTimesVisited();
        }
    }
}
