package com.fontys.kwetter.dao.memory;

import com.fontys.kwetter.dao.KweetDAO;
import com.fontys.kwetter.domain.Kweet;
import com.fontys.kwetter.domain.User;

import javax.ejb.Stateful;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arnoud Bevers
 * @project kwetter
 */
@Stateful
@Default
public class KweetDAOMemoryImpl implements KweetDAO {

  private List<Kweet> allKweets = new ArrayList<>();

  @Override
  public List<Kweet> getAll() {
    return allKweets;
  }

  @Override
  public List<Kweet> getKweetsForUser(User user) {
    List<Kweet> kweetsForUser = new ArrayList<>();
    for (Kweet k : allKweets) {
      if (k.getSender().equals(user)) {
        kweetsForUser.add(k);
      }
    }
    return kweetsForUser;
  }

  @Override
  public Kweet getKweetById(int id) {
    for (Kweet k : allKweets) {
      if (k.getId().equals(id)) {
        return k;
      }
    }
    return null;
  }

  @Override
  public List<Kweet> searchKweets(String searchString) {
    List<Kweet> kweetsWithSearchString = new ArrayList<>();
    for (Kweet k : allKweets) {
      if (k.getMessage().contains(searchString)) {
        kweetsWithSearchString.add(k);
      }
    }
    return kweetsWithSearchString;
  }

  @Override
  public void postKweet(Kweet kweet) {
    allKweets.add(kweet);
  }

  @Override
  public void removeKweet(Kweet kweet) {
    allKweets.remove(kweet);
  }
}
