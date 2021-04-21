# Setting up your own GitHub Account

![Approved for: Fall 2020](https://img.shields.io/badge/Approved%20for-Fall%202020-blueviolet)

In this tutorial, we will walk you through creating your own GitHub account, creating a private repository 
(so your code won't be publicly visible on the web), and cloning and pushing content to your private, 
online repository.

**Note**: You should never create a public repository for work that you are doing for a class.

## Setting up an Account

As students, you receive **free access** to [GitHub Pro](https://github.com/pricing) while you are a student.
To set up your account:

1. Make a normal GitHub account using your `@uga.edu` email address here: https://github.com/join 
1. Go to the Education Pack "join page" on GitHub: https://education.github.com/pack/join
1. Click on "Get your Pack".
1. Click on “Get Student Benefits” and Sign In with your Account, if needed.
1. Fill out the form and submit for review
1. Wait on email confirmation from GitHub. Hopefully, this will take less than a day.

## Setting up SSH Keys

Before you can push and pull to private repositories hosted on GitHub from your Odin account, you
will need to create an SSH public/private key pair on Odin. This will allow you to give a public
key to GitHub (think of it as a padlock) that GitHub can use to authenticate your requests in
addition to or in lieu of your GitHub username and password. It's sufficient to think of this 
key-based authentication process as you unlocking the public key (i.e., the padlock) using your
private key -- the Git program will do this with GitHub so long as the key pair is setup correctly. 

1. Login to Odin.

1. **Before you type the following command,** please note that it will prompt you to answer
   questions. 
   
   * Do **NOT** change any values when prompted -- simply press return until the `ssh-keygen`
     command is finished executing. 
   * When asked for a password, **simply press return** -- do not enter a password. 
   
   With all of that in mind, please execute the command below, replacing `your_email@uga.edu` 
   with your `@uga.edu` email address.
   
   ```
   $ ssh-keygen -t ed25519 -C "your_email@uga.edu"
   ```
  
   This creates a public / private key pair in the default location: `~/.ssh/`.
  
1. View your public key using `cat` and copy its output to your clipboard (usually by selecting
   the text in your terminal, then right clicking on the selection and clicking "Copy"):

   ```
   $ cat ~/.ssh/id_ed25519.pub
   ```
   
1. Login to [GitHub](https://www.github.com/).

1. In the upper-right corner of any page, click your profile photo, then click **Settings**.

1. In the user settings sidebar, click **SSH and GPG keys**.

1. Click **New SSH key or Add SSH key**.

1. In the "Title" field, add a descriptive label for the new key. 
   For example, if you're adding a key for your Odin account, then you might call this 
   key "Odin".
   
1. Paste your key into the "Key" field. This should be the copied output from your prior
   call to `cat`.
   
1. Click **Add SSH key**.

1. If prompted, confirm your GitHub password.

1. On Odin, verify that your key pair is setup correctly by trying to SSH to
   `git@github.com` (do not change the username):
   
   ```
   $ ssh git@github.com
   ```
   
   * You may see something like the following:
     
     ```
     The authenticity of host 'github.com (www.xxx.yyy.zzz)' can't be established.
     RSA key fingerprint is ...
     ...
     Are you sure you want to continue connecting (yes/no)?
     ```
     
     Type `yes`, then press return. It may emit the following warning, but
     that's okay and to be expected:
     
     ```
     Warning: Permanently added 'bitbucket.org,...`
     ```
   
   * If you see something similar to the following, then you should be okay to proceed:
   
     ```
     PTY allocation request failed on channel 0
     Hi <YourGitHubUsername>! You've successfully authenticated, but GitHub does not provide shell access.
     Connection to github.com closed.
     ```
     
   * If you see the following, then something went wrong:

     ```
     Permission denied (publickey).
     ```

## Creating a Repository

1. Login to [GitHub](https://www.github.com/)
1. Click on "New" under the repositories section on the left-hand side of the page.
1. You should see a screen similar to the image below. Type `cs1302-testRepo`
   as the name of your repository, click the "private" radio button and then press
   the "Create Repository" button.

![Create Repo](https://github.com/cs1302uga/cs1302-tutorials/raw/master/img/create_repo.png)

1. Once you've clicked the "Create Repository" button, your repository is created
   but still needs to be setup. GitHub should be showing a website which gives you
   a few options on how to setup your repository. Instead of following those instructions
   directly, we will setup our repository from Odin using the following commands:
   
   1. Clone your repository on Odin using `git clone git@github.com:username/cs1302-testRepo.git`
      replacing `username` with your GitHub username. You may get a message saying
      you've cloned an empty repository. That's okay! You should now have a folder
      called `cs1302-testRepo`.
   1. Change into the `cs1302-testRepo` directory.
   1. Rename the main branch to `main` using `git branch -M main`.
   1. Create an initial `README` file using `echo "# cs1302-testRepo" >> README.md`.
   1. Add your `README` file to the repository using `git add README.md`.
   1. Commit your changes using `git commit -am "first commit"`.
   1. Push your changes to GitHub using `git push origin main`
   1. Refresh your GitHub page to see the changes to the repository. Your repository should
      look similar to the below image. Note the number of commits (1 so far) to this
      repository, the latest commit time, and the contents of the `README` file are
      all shown on `GitHub`.
      
![Test Repo](https://github.com/cs1302uga/cs1302-tutorials/raw/master/img/testRepo.png)


1. Now that your repository is set up, let's get some more pracice. On Odin, do the following:
   1. Open your `README.md` file.
   1. Type a few additional sentences.
   1. Commit your changes locally.
   1. Push your changes to GitHub.
   1. Refresh the GitHub page to make sure your changes have been added to the online repository.
   1. Delete the entire `cs1302-testRepo` folder from Odin.
   1. Wait. Delete my entire project from Odin?!? Yep, do it.
   1. Execute the `git clone` command from above. And it's back!
   1. Note: you could clone from any machine that has `git` installed. You've all been cloning
      cs1302 GitHub repositories all semester.
      
Congratulations on becoming a member of GitHub!

<hr/>

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](http://creativecommons.org/licenses/by-nc-nd/4.0/)

<small>
Copyright &copy; Michael E. Cotterell, Brad Barnes, and the University of Georgia.
This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a> to students and the public.
The content and opinions expressed on this Web page do not necessarily reflect the views of nor are they endorsed by the University of Georgia or the University System of Georgia.
</small>
