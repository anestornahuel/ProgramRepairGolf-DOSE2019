/** db-attributes of userStats:
 * id
 * user_id
 * created_challenges
 * solved_challenges
 * current_points
 * created_at
 * updated_at
 */

package unrc.dose;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DBException;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 * Class for UserStat.
 * @author Nahuel Alvarez, Borda Agustin, Castillo Conrado
 */
public class UserStat extends Model {
/* Validators for model */
static {
    validatePresenceOf("user_id");
    validatePresenceOf("created_challenges");
    validatePresenceOf("solved_challenges");
    validatePresenceOf("current_points");
}
    /**
     * Gets the id of the user who has assigned this statistics.
     * in the database.
     * @return The id of the user.
     */
    public int getUserId() {
        return this.getInteger("user_id");
    }

    /**
     * Gets the number of created challenges by the user
     * who has assigned this statistics in the database.
     * @return The number of created challenges.
     */
    public int getCreatedChallenges() {
        return this.getInteger("created_challenges");
    }

    /**
     * Gets the number of solved challenges by the user
     * who has assigned this statistics in the database.
     * @return The number of solved challenges.
     */
    public int getSolvedChallenges() {
        return this.getInteger("solved_challenges");
    }

    /**
     * Gets the points that the user earned solving challenges.
     * @return The points of the user.
     */
    public int getCurrentPoints() {
        return this.getInteger("current_points");
    }

    /**
     * Show all UserStat registered in the system.
     * @return LazyList with all the UserStat.
     */
    public static LazyList<UserStat> showAllUserStat() {
        LazyList<UserStat> users = UserStat.findAll();
        return users;
    }

    /**
     * Create the statistics for a given user.
     * @param id The id of the user who statistics we will create.
     */
    public static void createUserStat(final int id) {
        UserStat stat = new UserStat();
        stat.set("user_id", id);
        stat.set("created_challenges", 0);
        stat.set("solved_challenges", 0);
        stat.set("current_points", 0);
        Base.openTransaction();
        try {
            stat.saveIt();
            Base.commitTransaction();
        } catch (DBException e) {
            Base.rollbackTransaction();
        }
    }

    /**
     * This method return the statics of the user.
     * @param user The user from who wants to get the statistics
     * @return Statistics of the user
     */
    public static UserStat getUserStat(final User user) {
        return UserStat.findFirst("user_id = ?", user.getId());
    }

}
