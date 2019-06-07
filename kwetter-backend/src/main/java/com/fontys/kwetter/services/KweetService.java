package com.fontys.kwetter.services;

import com.fontys.kwetter.dao.KweetDAO;
import com.fontys.kwetter.domain.Kweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Service for the Kweet model related methods defined in the DAO.
 * Used by the API controllers to call the needed methods.
 *
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateless @Named("kweetService")
public class KweetService {

  @Inject @Named("kweetDAO")
  private KweetDAO kweetDAO;

  public void postKweet(Kweet kweet) {
    kweetDAO.postKweet(kweet);
  }

  // TODO: Add indexing to this method (e.g. get kweets by groups of 1000)
  public List<Kweet> getAllKweets() {
    return kweetDAO.getAll();
  }

  public List<Kweet> searchKweets(String searchString) {
    return kweetDAO.searchKweets(searchString);
  }
}
