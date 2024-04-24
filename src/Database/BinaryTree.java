package Database;


/**
 * Uses a binary search tree to store a database of
 * PlayStation users. Nodes are ordered by user unique key (see the
 * User class for more detail).
 * Created for Data Structures, SP2 2017
 * @author Kieran Hambledon hamkj008
 * @version 1.0
 */
public class BinaryTree {
	
	public User root;
	
	
	private boolean success;
	private boolean heightIncrease;
	private boolean rootVisited;
	private int count;
	private int maxNum = 0;
	private Trophy t;
	private User winner;

	private StringBuilder sb = new StringBuilder();
	
	public BinaryTree() {}
	

	/**
	 * Making new friends is great. This method should add your new
	 * bestie to your database (tree). Remember that they should be
	 * added according to their key.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean beFriend(User friend) throws IllegalArgumentException {
		// No backtracking needed so iterative method used.
		
		// A local User to traverse the tree.
		User localRoot = root;
		// Boolean to return successful insertion.
		success = false;
		
		if(friend == null) {
			throw new IllegalArgumentException();
		}
		
		while(success != true) {
			// If the root is empty, friend to add becomes the root.
			if(root == null) {
				root = friend;
				success = true;
				return success;
			}
			// If the friend is already in the list, don't add to tree.
			else if(localRoot.equals(friend)) {
				System.out.println("Already in list");
				success = false;
				return success;
			}
			
			// If the friend to add has a lower key value, place it on the left side of the tree.
			else if(localRoot.getKey() > friend.getKey()) {
				// Empty spot found.
				if(localRoot.getLeft() == null) {
					// Add friend
					localRoot.setLeft(friend);
					success = true;
					return success;
				}
				// Keep traversing the left side.
				else {
					localRoot = localRoot.getLeft();
				}
			}
			// The friend to add has a higher key value. Place it to the right of the tree.
			else {
				// Empty spot found
				if(localRoot.getRight() == null) {
					// Add friend
					localRoot.setRight(friend);
					success = true;
					return success;
				}
				// Keep traversing the right side.
				else {
					localRoot = localRoot.getRight();
				}
			}
		}
		return success;				
	}
	

	/**
	 * Sometimes friendships don't work out. In those cases it's best
	 * to remove that "friend" altogether. This method should remove
	 * all trace of that "friend" in the database (tree).
	 * @param friend the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException if "friend" is null
	 */
	public boolean deFriend(User friend) throws IllegalArgumentException {
		// Iterative method used.
		
		// A local User to traverse the tree.
		User localRoot = root;
		// The parent of the local User.
		User parent = root;
		// Boolean to return successful insertion.
		success = false;
		
		if(friend == null) {
			throw new IllegalArgumentException();
		}

		while(success != true) {
			// If root is empty then cannot remove any friends.
			if(root == null) {
				success = false;
				return success;
			}
			// If the User is not found in the tree
			if(localRoot == null) {
				success = false;
				return success;
			}
			
			if(localRoot.equals(friend)) {
				// The Friend User to delete has been found. 
					// If it is a leaf node i.e. no children
					if(localRoot.getLeft() == null && localRoot.getRight() == null) {
						// The user can be easily removed.
						
						// If there is only one User in the tree, set to empty tree.
						if(localRoot.equals(root)) {
							root = null;
							success = true;
							return success;
						}
						// If the localRoots key is less than the parent, remove the User by
						// cutting the link on the left side.
						if(localRoot.getKey() < parent.getKey()) {
							localRoot = null;
							parent.setLeft(null);
							success = true;
							return success;
						}
						else {
							// Remove the User by cutting the link on the right side.
							localRoot = null;
							parent.setRight(null);
							success = true;
							return success;
						}
					}
					// If it has one child on the right
					else if(localRoot.getLeft() == null && localRoot.getRight() != null) {
						if(localRoot.equals(root)) {
							root = localRoot.getRight();
						}
						// Link the Left-Right case
						if(localRoot.getKey() < parent.getKey()) {
							parent.setLeft(localRoot.getRight());							
						}
						else {
							// Link the Right-Right case
							parent.setRight(localRoot.getRight());
						}
						// Remove links to tree
						localRoot.setLeft(null);
						localRoot.setRight(null);
						success = true;
						return success;
					}
					// If it has one child on the left
					else if(localRoot.getLeft() != null && localRoot.getRight() == null) {
						if(localRoot.equals(root)) {
							root = localRoot.getLeft();
						}
						// Link the Left-Left case
						if(localRoot.getKey() < parent.getKey()) {
							parent.setLeft(localRoot.getLeft());							
						}
						else {
							// Link the Right-Left case
							parent.setRight(localRoot.getLeft());
						}
						// Remove links to tree
						localRoot.setLeft(null);
						localRoot.setRight(null);
						success = true;
						return success;
					}
					// It has two children. 
					else {
						// If its left child has no right child, it is the inorder predecessor
						if(localRoot.getLeft().getRight() == null) {
							
							// Promote up the left child
							localRoot.getLeft().setRight(localRoot.getRight());
							
							if(localRoot.equals(root)) {
								root = localRoot.getLeft();
							}
							//  Set the parent of the localRoot to reference the left child.
							if(localRoot.getKey() < parent.getKey()) {
								parent.setLeft(localRoot.getLeft());
							}
							else {
								parent.setRight(localRoot.getLeft());
							}
							// Remove links to tree		
							localRoot.setLeft(null);
							localRoot.setRight(null);
							success = true;
							return success;
						}
						else {							
							// Find the rightmost node in the right subtree of the left child
							User rightFinder = localRoot.getLeft();
							User rightFindParent = rightFinder;
							
							// Keep going right untill can go no further.
							while(rightFinder.getRight() != null) {
								rightFindParent = rightFinder;
								rightFinder = rightFinder.getRight();
							}
							// Found the rightmost node. This will be promoted up. 
							// First store any left children in the parent
							rightFindParent.setRight(rightFinder.getLeft());
							// Link the rightFinder to its new place in the tree.
							rightFinder.setRight(localRoot.getRight());
							rightFinder.setLeft(localRoot.getLeft());
							// Set the new root if necessary.
							if(localRoot.equals(root)) {
								root = rightFinder;
							}
						
							// Remove the User from the tree
							parent.setLeft(rightFinder);
							// Remove links to tree
							localRoot.setLeft(null);
							localRoot.setRight(null);				
							success = true;
							return success;				
							}							
						}
					}

			// If the friend's key is less than the localRoot
			else if(friend.getKey() < localRoot.getKey()) {
				// Friend must be in the left subtree. Search the left subtree
				parent = localRoot;
				localRoot = localRoot.getLeft();				
			}
			else {
				// Friends key must be greater than the localRoot. 
				// Friend must be in the right subtree. Search the right subtree.
				parent = localRoot;
				localRoot = localRoot.getRight();
			}
		}
		return success;
	}

	/**
	 * In your quest to be the very best you need to know how many
	 * of your friends are ranked higher than you. This method should
	 * return the number of higher ranked users that the provided reference
	 * user, or zero if there are none (woot!).
	 * @param reference The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countBetterPlayers(User reference) throws IllegalArgumentException {
		if(reference == null) {
			throw new IllegalArgumentException();
		}
		
		// Count variable counts the number of better players.
		count = 0;
		// Checks whether the root has been visited to maintain in-order traversal.
		rootVisited = false;
		
		// Recursive method to count the better players.
		countBetterPlayers(root, reference);
		
		// Recursive method has checked the tree. 
		// If success is false then there are no better players. Returns -1
		if(success == false) {
			count = -1;
		}
		
		return count;
	}
	
	public User countBetterPlayers(User localRoot, User reference) {
		// Default terminating case.
		if(localRoot == null) {
			return localRoot;
		}
		// If the reference is in the tree
		if(localRoot.equals(reference)) {
			success = true;
		}
		
		if(rootVisited == false) {				
		// if there is an item on the left.
			if(localRoot.getLeft() != null) {				
				// Recursively head down the left side of the tree.
				// Stop when the reference has been found.
				if(localRoot.getLevel() > reference.getLevel()) {
					countBetterPlayers(localRoot.getLeft(), reference);
				}
				else {
					rootVisited = true;
				}
			}
			else {
				rootVisited = true;
			}
		}
		if(rootVisited == true) {
			// If the localRoot has a key value greater than the reference,
			// then localRoot is a better player and must be counted.
			if(localRoot.getLevel() > reference.getLevel()) {
				// Count the better players only if the reference is in the tree.
				if(success == true) {
					count++;
				}
			}
			// Check the right.
			if(localRoot.getRight() != null) {
				localRoot = localRoot.getRight();
				rootVisited = false;
			}
			else {				
				return localRoot;
			}			
		}
		return countBetterPlayers(localRoot, reference);
	}

	/**
	 * Bragging rights are well earned, but it's good to be sure that you're actually
	 * better than those over whom you're lording your achievements. This method
	 * should find all those friends who have a lower level than you, or zero if
	 * there are none (you suck).
	 * @param reference The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countWorsePlayers(User reference) throws IllegalArgumentException {
		// Starter method for countWorsePlayers
		if(reference == null) {
			throw new IllegalArgumentException();
		}
		// Reset count and rootVisited.
		// Count counts how many worse players
		count = 0;
		// rootVisited keeps track of whether the root of the subtree has been visited
		// to maintain proper in-order traversal
		rootVisited = false;
		
		countWorsePlayers(root, reference);
		
		// Recursive method has checked the tree. 
		// If success is false then there are no worse players. Returns -1
		if(success == false) {
			count = -1;
		}
		
		return count;
	}
	
	public User countWorsePlayers(User localRoot, User reference) {
		// Default terminating case
		if(localRoot == null) {
			return localRoot;
		}
		// If the reference is in the tree
		if(localRoot.equals(reference)) {
			success = true;
		}
		
		if(rootVisited == false) {				
		// if there is an item on the left.
			if(localRoot.getLeft() != null) {
				// Recursively head down the left side of the tree.
				// Stop when the reference has been found.
				countWorsePlayers(localRoot.getLeft(), reference);				
			}
			else {
				rootVisited = true;
			}
		}
		if(rootVisited == true) {
			// If the localRoot has a key value less than the reference,
			// then localRoot is a worse player and must be counted.
			if(localRoot.getLevel() < reference.getLevel()) {
				// Count the worse players only if the reference is in the tree.
				if(success == true) {
					count++;
				}
			}
			// Check the right.
			if(localRoot.getRight() != null) {
				localRoot = localRoot.getRight();
				rootVisited = false;
			}
			else {				
				return localRoot;
			}			
		}
		return countWorsePlayers(localRoot, reference);
	}

	/**
	 * The best player may not necessarily be measured by who has the highest level.
	 * Platinum trophies are the holy grail, so it would be good to know who has the
	 * most. This method should return the user with the highest number of platinum trophies.
	 * @return the user with the most platinum trophies, or null if there are none
	 */	
	public User mostPlatinums() {
		// Starter method for findPlatinums	
		rootVisited = false;
		
		// Enter recursive findPlatinums method
		findPlatinums(root);
		
		if(maxNum == 0) {
			return null;
		}
		return winner;
	}
	
	/**
	 * This recursive method finds the user with the most platinum trophies
	 * re above description. 
	 * @param localRoot
	 * @return the user with the most platinum trophies
	 */
	public User findPlatinums(User localRoot) {
		if(localRoot == null) {
			return localRoot;
		}
		
		if(rootVisited == false) {				
			// if there is an item on the left
			if(localRoot.getLeft() != null) {
				// Recursively search the left tree until the end is found.
				findPlatinums(localRoot.getLeft());
			}
			else {
				rootVisited = true;
			}
		}
		// End of the tree has been found. Start counting trophies.
		if(rootVisited == true) {
			count = 0;
			// Count the platinum trophies for each User node
			for(int i = 0; i < localRoot.getTrophies().size(); i++) {
				t = localRoot.getTrophies().get(i);
				if(t.getRank() == Trophy.Rank.PLATINUM) {
					count++;
				}
			}
			// If the User has more platinum trophies, that User becomes the winner.
			if(count > maxNum) {
				maxNum = count;
				winner = localRoot;
				return localRoot;
			}
			// If the amount of platinum trophies are tied, count the amount of gold trophies as a tie-breaker.
			else if(count == maxNum) {
				if(winner != null) {
					count = 0;
					int goldNum = 0;
					for(int j = 0; j < localRoot.getTrophies().size(); j++) {
						t = localRoot.getTrophies().get(j);
						if(t.getRank() == Trophy.Rank.GOLD) {
							count++;
						}
					}
					// Whichever User has more gold trophies becomes the winner.
					if(count > goldNum) {
						goldNum = count;
						winner = localRoot;
						return localRoot;
					}
				}			
			}
			// if there is an item on the right
			if(localRoot.getRight() != null) {
				// Head right
				localRoot = localRoot.getRight();
				// rootVisited becomes false which restarts the method to check the left again.
				rootVisited = false;
			}
			else {				
				return localRoot;
		
			}
		}
		return findPlatinums(localRoot);
	}

	/**
	 * You or one of your friends bought a new game! This method should add it to their
	 * GameList.
	 * @param username The user who has bought the game
	 * @param game The game to be added
	 */
	public void addGame(String username, Game game) throws IllegalArgumentException {
		if(username == null || game == null) {
			throw new IllegalArgumentException();
		}

		rootVisited = false;
		
		// Use findUser method to find and return the User that the username belongs to in the tree.
		// findUser updates User winner with the returned User.
		findUser(root, username);
		
		// Store the name of the game in order to feed it into getGame() method
		String gameName = game.getName();
		
		// If getGame() method returns null, then the User does have the game in their list
		// and can be safely added without duplicates.
		if(winner.getGames().getGame(gameName) == null) {
			winner.getGames().addGame(game);
		}					
	}
	
	/**
	 * This helper method uses recursion to traverse the tree, 
	 * as the User could be anywhere in the tree.
	 * It then locates and returns the User object that the username belongs to.
	 * @param localRoot
	 * @param username
	 * @return the User with the username.
	 */
	public User findUser(User localRoot, String username) {
		if(localRoot == null) {
			return localRoot;
		}
		// Perform an in-order traversal. Check left, check root, check right.
		if(rootVisited == false) {				
			// if there is an item on the left
			if(localRoot.getLeft() != null) {
				// Head left as far as possible.
				findUser(localRoot.getLeft(), username);
			}
			else {
				rootVisited = true;
			}
		}
		// Once the left has been searched, rootVisited is true
		if(rootVisited == true) {
			// Check the contents of the localRoot.
			// If the username has been found, set localRoot to equal winner.
			if(localRoot.getUsername().equals(username)) {
					winner = localRoot;
			}
			// Check the right side of the localRoot, if nothing there then return back up the tree.
			if(localRoot.getRight() != null) {
				localRoot = localRoot.getRight();
				rootVisited = false;
			}
			else {				
				return localRoot;
			}
		}
		return findUser(localRoot, username);
	}

	/**
	 * You or one of your friends achieved a new trophy! This method should add it to
	 * their trophies.
	 * @param username The user who has earned a new trophy
	 * @param trophy The trophy to be added   
	 */
	public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
		
		if(username == null || trophy == null) {
			throw new IllegalArgumentException();
		}
		
		// Use findUser method to find and return the User that the username belongs to in the tree.
		// findUser updates User winner with the returned User.
		findUser(root, username);
		
		
		// Check the winners trophy list to see if the trophy has already been acquired.
		boolean haveTrophy = false;	
		
		for(int i =0; i < winner.getTrophies().size(); i++) {
			t = winner.getTrophies().get(i);
			if(t.getName().equals(trophy.getName())) {
				haveTrophy = true;
			}
			if(t.getGame().equals(trophy.getGame())) {
				if(t.getRank().equals(trophy.getRank())) {
					haveTrophy = true;
				}
			}
		}
		// The winner doesn't already have the trophy so the trophy to be added can safely be added.
		if(haveTrophy == false) {
			winner.getTrophies().add(trophy);
		}				
	}	


	/**
	 * You or one of your friends has gained one level! This is great news, except that
	 * it may have ruined your tree structure! A node move may be in order.
	 * @param username The user whose level has increased
	 */
	public void levelUp(String username) throws IllegalArgumentException {
		
		if(username == null) {
			throw new IllegalArgumentException();
		}
		// Use findUser method to find and return the User that the username belongs to in the tree.
		// findUser updates User winner with the returned User.		
		findUser(root, username);
		
		// Use deFriend method to iteratively remove the User from the tree.
		deFriend(winner);
		
		// Increase the winners level by 1.
		winner.setLevel(winner.getLevel() +1);		
		
		// Use beFriend method to re-add the User to the tree. 
		// The method will find the new appropriate spot for the User based on the new level.
		// No other tree restructuring needed.
		beFriend(winner);
		
	}

	/**
	 * As your friends list grows, adding with regular binary tree rules will
	 * result in an unbalanced and inefficient tree. One approach to fix this
	 * is to implement an add method that uses AVL balancing. This method should
	 * work in the same way as beFriend, but maintain a balanced tree according to
	 * AVL rules.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		// addAVL starter method
		success = false;
		
		// If the tree is empty, add the friend to the root.
		if(root == null) {
			root = friend;
			success = true;
			return success;	
		}
		
		heightIncrease = false;
		
		// Enter recursive addAVL method to add friend.
		root = addAVL(root, friend);	
		
		// return the result of recursive addAVL method
		return success;	
	}
	
	/**
	 * This recursive method add the friend to the tree and re-balances it
	 * re above description.
	 * @param localRoot
	 * @param friend
	 * @return
	 */
	public User addAVL(User localRoot, User friend) {
		// Found empty spot to add localRoot
		if(localRoot == null) {
			// Item inserted
			localRoot = friend;
			success = true;
			heightIncrease = true;
			return localRoot;
		}
		// If the User is already in the tree
		else if(localRoot.equals(friend)) {
			heightIncrease = false;
			success = false;
			return localRoot;
		}
		// If User to be inserted has a lower key value.
		else if(friend.getKey() < localRoot.getKey()) {
			// Insert into the left tree
			localRoot.setLeft(addAVL(localRoot.getLeft(), friend));	
			
			// If the height of the tree has increased after successful insertion
			if(heightIncrease == true) {
				// Set the balance of the left tree and check if re-balancing is needed
				localRoot.setBalance(localRoot.getBalance() -1);
				if(localRoot.getBalance() == 0) {
					heightIncrease = false;
				}
				// Re-balancing required
				if(localRoot.getBalance() < -1) {
					heightIncrease = false;
					localRoot = rebalanceLeft(localRoot);
				}
			}
		}
		// If User to be inserted has a greater key value.
		else if(friend.getKey() > localRoot.getKey()){
			// Insert into the right tree
			localRoot.setRight(addAVL(localRoot.getRight(), friend));
			
			// If the height of the tree has increased after successful insertion
			if(heightIncrease == true) {
				// Set the balance of the right tree and check if re-balancing is needed
				localRoot.setBalance(localRoot.getBalance() +1);
				if(localRoot.getBalance() == 0) {
					heightIncrease = false;
				}
				// Re-balancing required
				if(localRoot.getBalance() > 1) {
					heightIncrease = false;
					localRoot = rebalanceRight(localRoot);
				}
			}
		}
		return localRoot;	
	}
	
	/**
	 * This helper method re-balances the left subtree according to the balance 
	 * number of the localRoot
	 * @param localRoot
	 * @return the re-balanced subtree
	 */
	public User rebalanceLeft(User localRoot) {
		if(localRoot.getLeft() != null) {
			// Left-Right Case
			if(localRoot.getLeft().getBalance() > 0) {
				if(localRoot.getLeft().getLeft() != null) {
					// Left-Right-Left Case
					if(localRoot.getLeft().getLeft().getBalance() < 0) {
						localRoot.getLeft().setBalance(0);
						localRoot.getLeft().getLeft().setBalance(0);
						localRoot.setBalance(1);
					}
					// Left-Right-Right Case
					else {
						localRoot.getLeft().setBalance(-1);
						localRoot.getLeft().getLeft().setBalance(0);
						localRoot.setBalance(0);
					}
					// Rotate left around left subtree root
					
					// Store the reference to the root. 
					// Select the rotation node.
					User temp = localRoot.getRight();
					// Cut the right link
					localRoot.setRight(temp.getLeft());
					// Set the rotation nodes right child
					temp.setLeft(localRoot);
					// Reconnect to the root.
					localRoot = temp;
				}
			}
			// Left-Left Case
			else {
				localRoot.getLeft().setBalance(0);
				localRoot.setBalance(0);
				
				// Rotate right
				
				// Store the reference to the root. 
				// Select the rotation node.
				User temp = localRoot.getLeft();
				// Cut the left link
				localRoot.setLeft(temp.getRight());
				// Set the rotation nodes right child
				temp.setRight(localRoot);
				// Reconnect to the root.
				localRoot = temp;
			}
		}
		return localRoot;
	}

	/**
	 * This helper method re-balances the right subtree according to the balance 
	 * number of the localRoot
	 * @param localRoot
	 * @return the re-balanced subtree
	 */
	public User rebalanceRight(User localRoot) {
		if(localRoot.getRight() != null) {
			// Right-Left Case
			if(localRoot.getRight().getBalance() < 0) {
				if(localRoot.getRight().getRight() != null) {
					// Right-Left-Right Case
					if(localRoot.getRight().getRight().getBalance() > 0) {
						localRoot.getRight().setBalance(0);
						localRoot.getRight().getRight().setBalance(0);
						localRoot.setBalance(-1);
					}
					// Right-Left-Left Case
					else {
						localRoot.getRight().setBalance(1);
						localRoot.getRight().getRight().setBalance(0);
						localRoot.setBalance(0);
					}
					// Rotate right around right subtree root
					
					// Store the reference to the root. 
					// Select the rotation node
					User temp = localRoot.getLeft();
					// Cut the left link
					localRoot.setLeft(temp.getRight());
					// Set the rotation nodes right child
					temp.setRight(localRoot);
					// Reconnect to the root.
					localRoot = temp;
				}
			}
			// Right-Right Case
			else {
				localRoot.getRight().setBalance(0);
				localRoot.setBalance(0);
				
				// Rotate Left
				
				// Store the reference to the root. 
				// Select the rotation node
				User temp = localRoot.getRight();
				// Cut the right link
				localRoot.setRight(temp.getLeft());
				// Set the rotation nodes left child
				temp.setLeft(localRoot);
				// Reconnect to the root.
				localRoot = temp;				
			}
		}
		return localRoot;
	}
	
	/**
	 * A nice, neat print-out of your friends would look great in the secret scrap-book
	 * that you keep hidden underneath your pillow. This method should print out the
	 * details of each user, traversing the tree in order.
	 * @return A string version of the tree
	 */
	public String toString() {
		// Starter method for getTreeString()
	
		rootVisited = false;
		
		// Enter recursive method to get the toString.
		getTreeString(root);
		
		// Store the concatenated tree toString
		String finalString = sb.toString();
		// Trim off the trailing whitespace
		finalString = finalString.trim();
		
		return finalString;
	}
	
	/**
	 * This recursive helper method uses an in-order traversal to traverse the tree 
	 * and appends the content of each localRoot to a string builder 
	 * to return the toString of the entire tree, re above description.
	 * @param localRoot
	 * @return
	 */
	public User getTreeString(User localRoot) {
		// Default terminating case.
		if(localRoot == null) {
			return localRoot;
		}
		
		if(rootVisited == false) {				
		// if there is an item on the left
			if(localRoot.getLeft() != null) {				
				getTreeString(localRoot.getLeft());
			}
			else {
				rootVisited = true;
			}
		}
		// Boolean printed stops the method from appending the same node twice.
		boolean printed = false;
		// Append the localRoots toString
		if(printed == false) {
			sb.append(localRoot + "\n");
			printed = true;
		}
		// Check the right side.
		if(localRoot.getRight() != null) {
			localRoot = localRoot.getRight();
			rootVisited = false;
		}
		else {
			printed = false;
			return localRoot;
		}			
	return getTreeString(localRoot);
	}	

}
