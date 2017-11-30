public class UserTree {


		User root;

		/**
		 * used to add a root to a empty tree. 
		 * 
		 * @param newRoot new root of the tree
		 */
		private void addRoot(User newRoot) {
			
			this.root = newRoot;
			newRoot.parent = null;
			newRoot.rightChild = null;
			newRoot.middleChild = null;
			newRoot.leftChild = null;
			return;
		}

		/**
		 * this function transforms a string into a char array.
		 * it is used to compare strings later.
		 * @param String userName
		 * @return char[]
		 */
		private char[] StringtoChar(String userName){
			char[]  userNameCharArray = userName.toCharArray();
			return userNameCharArray;
		}
		
		/**
		 * This function searches the tree for a User using its userName.
		 * 
		 * @param userName the Name of the User (String)
		 * 
		 * @return null if the User is not in the database.
		 * 			the pointer to the User object if it is.
		 */
		
		protected User findUser(String userName){
			int i =0;
			User searchUser = root;
			char[] userNameCharArray= StringtoChar(userName);
			while(searchUser != null && !(searchUser.userName.equals(userName))) {
				char[]searchUserChar= StringtoChar(searchUser.userName);
				
				if(searchUserChar[i] > userNameCharArray[i]) 
				{
					searchUser = searchUser.leftChild;
				} else {
					if(searchUserChar[i] < userNameCharArray[i]) 
					{
						searchUser = searchUser.rightChild;
					} 
					else {
					searchUser = searchUser.middleChild;
					i++;
					}
				}
			}
			return searchUser;
		}

		/**
		 * this function adds  new user to the tree as a leaf.
		 * 
		 * @param User user to be added to the tree.
		 */
		protected void addUser(User user) {
			int i=0;
			if (root == null) {
				addRoot(user);
				return;
			}
			
			User searchUser = root;
			char[] userNameChar= StringtoChar(user.userName);
			while(!(searchUser.userName.equals(user.userName))) {
				char[]searchUserChar= StringtoChar(searchUser.userName);
				if(searchUserChar[i] > userNameChar[i]) {
					if(searchUser.leftChild == null){
						searchUser.leftChild = user;
						user.parent = searchUser;
						return;
					} else {
						searchUser = searchUser.leftChild;
					}
					
				} else {
					if(searchUserChar[i] < userNameChar[i]) {
						if(searchUser.rightChild == null){
							searchUser.rightChild = user;
							user.parent = searchUser;
							return;
						} else {
							searchUser = searchUser.rightChild;
							}
					} else {
						if(searchUser.middleChild == null){
							searchUser.middleChild = user;
							user.parent = searchUser;
							return;
						} else {
							searchUser = searchUser.middleChild;
							i++;
							}
						
					}
				}
			}
			return;
		}
		/**
		 * rekusive function to find all nodes in the tree. 
		 * almost in correct alphabetical order.
		 * 
		 * @param User node 
		 */
		private void TreeRekusiv(User node){
			if(root.userName.equals(node.userName)){
			System.out.println(node.userName); 
			}
			if(node.leftChild!=null){
				System.out.println(node.leftChild.userName);
				TreeRekusiv(node.leftChild);
			}
			
			if(node.middleChild!=null){
				System.out.println(node.middleChild.userName);
			TreeRekusiv(node.middleChild);
			}
			if(node.rightChild!= null){
				System.out.println(node.rightChild.userName);
				TreeRekusiv(node.rightChild);
			}
		}
		/**
		 * show all user in the System
		 */
		protected void showAllUser(){
			TreeRekusiv(root);
		}
}
