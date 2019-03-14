package dao;

import domain.Kweet;
import domain.User;

import java.util.List;


/**
 * DAO interface containing all methods related to the Kweet model.
 * @author Arnoud Bevers
 * @project kwetter
 */
public interface KweetDAO {

  /**
   * Gets all kweets.
   *
   * @return List of all kweets.
   */
  List<Kweet> getAll();

  /**
   * Gets all kweets posted by a user.
   *
   * @param user The user whose kweets to get.
   * @return List of kweets posted by the given user.
   */
  List<Kweet> getKweetsForUser(User user);

  /**
   * Gets a kweet by its id.
   *
   * @param id The id of a kweet.
   * @return Kweet object corresponding to given id.
   */
  Kweet getKweetById(int id);

  /**
   * Searches through all kweets with a given search string.
   * @param searchString Search option to get all kweets by.
   * @return List with all kweets containing the given search string.
   */
  List<Kweet> searchKweets(String searchString);

  /**
   * Posts a kweet.
   *
   * @param kweet Kweet to be posted.
   */
  void postKweet(Kweet kweet);

  /**
   * Removes a kweet.
   *
   * @param kweet Kweet to be removed
   */
  void removeKweet(Kweet kweet);

//  void likeKweet(User user, Kweet kweet);
}
