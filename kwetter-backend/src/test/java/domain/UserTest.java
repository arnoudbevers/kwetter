package domain;

import com.fontys.kwetter.domain.Role;
import com.fontys.kwetter.domain.User;
import com.fontys.kwetter.exceptions.FollowException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserTest {

  private List<User> users;

  @Before
  public void setUp() {
    this.users = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      User u = new User(Role.USER, "test" + i + "@mail.com", "User" + i, "password" + i );
      u.setId((long) i);
      users.add(u);
    }

    for (int i = 0; i < users.size(); i++) {
      User u = users.get(i);
      for (int j = 0; j < users.size(); j++) {
        if (j != i) {
          try {
            u.follow(users.get(j));
          } catch (FollowException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  @Test
  public void testInitialization() {
    for (int i = 0; i < users.size(); i++) {
      User u = users.get(i);
      Assert.assertEquals(Math.toIntExact(u.getId()), i);
      Assert.assertEquals(u.getUsername(), "User" + i);
      Assert.assertEquals(u.getEmail(), "test" + i + "@mail.com");
      Assert.assertEquals(u.getRole(), "User");
      Assert.assertEquals(u.getPassword(), "password" + i);
//       Assert.assertEquals(u.getPicture(), "www.picture" + i + ".com");
      Assert.assertEquals(u.getKweets(), new ArrayList<>());
      Assert.assertEquals(9, u.getFollowers().size());
      Assert.assertEquals(9, u.getFollowing().size());
    }
  }

  @Test
  public void testShouldFollowUser() {
    for (User u : users) {
      u.setFollowers(new ArrayList<>());
      u.setFollowing(new ArrayList<>());
    }
   for (int i = 0; i < users.size(); i++) {
      User u = users.get(i);
      for (int j = 0; j < users.size(); j++) {
        if(i != j) {
          try {
            u.follow(users.get(j));
          } catch (FollowException e) {
            e.printStackTrace();
          }
        }
      }
    }
    // Each user should have 9 following and 9 followers
    for (User u : users) {
      Assert.assertEquals(9, u.getFollowers().size());
      Assert.assertEquals(9, u.getFollowing().size());
    }
  }

//  @Test(expected = FollowException.class)
//  public void testUserCannotFollowItself() throws FollowException {
//    for (User u : users) {
//      u.follow(u);
//    }
//  }

}
