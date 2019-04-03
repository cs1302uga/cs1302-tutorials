# Setting up your own GitHub Account

In this tutorial, we will walk you through creating your own GitHub account, creating a private repository 
(so your code won't be publicly visible on the web), and cloning and pushing content to your private, 
online repository.

**Note**: You should never create a public repository for work that you are doing for a class.

## Setting up an Account

As students, you receive **free access** to [GitHub Pro](https://github.com/pricing) while you a student.
To set up your account:

1. [Click Here](https://education.github.com/pack/join)
1. Click on "Yes, I'm a Student"
1. Fill out the form and submit for review
1. Wait on email confirmation from GitHub. Hopefully, this will take less than a day.

## Setting up SSH Keys

Before you can push and pull to private repositories hosted on GitHub from your Nike account, you
will need to create an SSH public/private key pair on Nike. This will allow you to give a public
key to GitHub (think of it as a padlock) that GitHub can use to authenticate your requests in
addition to or in lieu of your GitHub username and password. It's sufficient to think of this 
key-based authentication process as you unlocking the public key (i.e., the padlock) using your
private key -- the Git program will do this so long as the key pair is setup correctly. 

1. Login to Nike.

1. Execute the command below, replacing `your_email@uga.edu` with your `@uga.edu` email address.
   Do **NOT** change any values when prompted -- simply press return until the command is finished
   executing.

   ```
   $ ssh-keygen -t rsa -b 4096 -C "your_email@euga.edu"
   ```
  
   This creates a public / private key pair in the default location: `~/.ssh/`.
  
1. View your public key using `cat` and copy its output to your clipboard (usually by selecting
   the text in your terminal, then right clicking on the selection and selecting "Copy"):

   ```
   $ cat ~/.ssh/id_rsa.pub
   ```
   
1. Login to [GitHub](https://www.github.com/).

1. In the upper-right corner of any page, click your profile photo, then click **Settings**.

1. In the user settings sidebar, click **SSH and GPG keys**.

1. Click **New SSH key or Add SSH key**.

1. In the "Title" field, add a descriptive label for the new key. 
   For example, if you're adding a key for your Nike account, then you might call this 
   key "Nike".
   
1. Paste your key into the "Key" field. This should be the copied output from your prior
   call to `cat`.
   
1. Click **Add SSH key**.

1. If prompted, confirm your GitHub password.

## Creating a Repository

1. Login to [GitHub](https://www.github.com/)
1. Click on "New" under the repositories section on the left-hand side of the page.
1. You should see a screen similar to the image below. Type `cs1302-test-repo`
   as the name of your repository, click the "private" radio button and then press
   the "Create Repository" button.

![Create Repo](https://github.com/cs1302uga/cs1302-tutorials/raw/master/img/create_repo.png)
