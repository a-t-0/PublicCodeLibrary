package com.example.a.test8;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.NetworkErrorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    /**
     * Main activity is auto generated by android Studio as the basic screen with a single button.
     *  If the button is pressed it will firstt execute the method: getAccountList0()
     *  Then it will try the method removeAccounts()
     *  If both methods have been executed succesfully, the app returns a message saying the methods
     *  have been executed successfully.
     *
     *  Methods:
     *  0. onCreate - is the standard template of android button, it is the module that creates a
     *  blank screeen with single push button.
     *      0.a Oncreate calls removeAccounts attempt 0.
     *
     *  1. writeToFile() it is a method to allow for debugging, it returns pop up messenges during
     *     code execution.
     *
     *  3. removeAccountA-D are called from getAccountLists to check if one of the getAccountLists has a
     *
     *
     *  2. getAccountList0() - it succesfully loops through all the accounts on the phone, has filter
     *  that only passes the davdroid accounts on to the removal methods. The following methods are
     *  called as a function of an object (the AccountManager object) in getAccountList0():
     *      2.a the remove attempts 2a
     *      2.b remove attempt 2b
     *      2.c remove attempt 2c
     *  The following separate/independent methods are called in getAccountList0():
     *      2.d
     *      2.e
     *      2.f
     *
     *  2. getAccountList2() is called by onCreate and it contains 3 direct functions:
     *      2.a method removeAccounts0() it removes acocunts with command removeAccountsExplicitly
     *      2.b removeAccounts1(); it removes accounts with the removeAccount method(API level <22)
     *      2.c. removeAccounts2(); it removes accounts with the removeAccount method (API level>22)
     *  getaccountlist2 contains  2 methods which CALL the removal from the bojects itself
     *  //remove7 and remove8.
     *
     *  Problems:
     *  0. I think the main problem is that there is no authentication I do not know how that works in this app/code.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAccountList0();
                getAccountList2(); //This should be sufficient to remove the accounts, but it does not do so.
                writeToFile("Method getAccountList0 and removeAccounts have been executed successfullly.");
            }
        });
    }


    /**
     * This is the "System.out.println" of android apps, it displays the message during execution.
     * @param message
     */
    public void writeToFile(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    //Used for method removeAccounts0
    // Global Variables
    public String AUTHORITY = "com.example.test8";
    public String ACCOUNT_TYPE = "at.bitfire.davdroid.address_book";
    public String ACCOUNT = "Address Book (P2:Own:Alg. Data Struc ng)";


    //Source:https://stackoverflow.com/questions/22174259/pick-an-email-using-accountpicker-newchooseaccountintent
    public void getAccountList0(){
        int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.GET_ACCOUNTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.GET_ACCOUNTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }
        String possibleEmail="";
        possibleEmail += "************* Get Registered Gmail Account *************\n\n";
        Account[] accounts = AccountManager.get(this).getAccountsByType("bitfire.at.davdroid"); //Filter on only davdroid (adress book) accounts

        //Account systemAccount = new Account(mainAccount.getDisplayName(),                getResources().getString(R.string.account_type));
        for (Account account : accounts) {
            writeToFile("Call removal method with: "+account.name+" and type = "+account.type); //System out calender name

            //Try removals from an object itself:
            //Attempt 0.a:
            AccountManager.get(this).removeAccountExplicitly(account);
            // Attempt 0.b:
            AccountManager.get(this).removeAccount(account,null, null, null);
            //Attempt 0.c:
            AccountManager.get(this).removeAccount(account,null, null);

            //Attempt 6:

            removeAccountd(account.name,account.type);

            //System.out.println which calendar has been removed.
            writeToFile(account.name);
            possibleEmail += " --> "+account.name+" : "+account.type+" , \n";
            possibleEmail += " \n\n";

        }
    }

    /**
     * Source:
     * This method appears to have a problem at the == AUTHORITY line. Cartman prevents the execution
     * of the removeAccount function. But if you take the "removeAccountFunction outside the if-statement
     * the app crashes when it is executed.
     */
    public void getAccountList2(){
        // Account Manager definition
        AccountManager accountManager = (AccountManager) this.getSystemService(ACCOUNT_SERVICE);

        // loop through all accounts to remove them
        Account[] accounts = accountManager.getAccounts();
        int Temp = accounts.length;
        writeToFile(String.valueOf(Temp));
        //for (int index = 0; index < accounts.length; index++) {
        for (int index = 0; index < Temp; index++) {
            writeToFile(accounts[index].name);
            if (accounts[index].type.intern() == AUTHORITY) {


                removeAccountA(accountManager, accounts[index]);
                removeAccountB(accountManager, accounts[index]);
                removeAccountC(accountManager, accounts[index]);
                removeAccountD(accounts[index].name.toString(),accounts[index].type.toString());
            }
        }
    }



    public void removeAccountA(AccountManager accountManager ,Account removalAccount){
        accountManager.removeAccountExplicitly(removalAccount);
    }

    public void removeAccountB(AccountManager accountManager ,Account removalAccount){
        accountManager.removeAccount(removalAccount, null, null);
    }

    public void removeAccountC(AccountManager accountManager ,Account removalAccount){
        accountManager.removeAccount(removalAccount,null, null, null);
    }

    public void removeAccountD(String name,String type){
        //am =AccountManager.get(Context.ACCOUNT_SERVICE);
        AccountManager am = (AccountManager) this.getSystemService(ACCOUNT_SERVICE);
        Account mAccount=new Account(name,type);
        am.removeAccount(mAccount,new AccountManagerCallback<Boolean>(){
                    public void run(    AccountManagerFuture<Boolean> future){
                        boolean failed=true;
                        try {
                            if (future.getResult() == true) {
                                failed=false;
                            }
                        }
                        catch (      OperationCanceledException e) {
                        }
                        catch (      IOException e) {
                        }
                        catch (      AuthenticatorException e) {
                        }
                    }
                }
                ,null);
    }

    //New attempts
    //Source: http://www.javased.com/index.php?api=android.accounts.AccountManager
    /**
     * delete an account
     * @param name : account name
     * @param type : account type
     */
    public void getAccountList1(String name,String type){
        //Question 0.: What is the "mContext"?
        //AccountManager am=(AccountManager)mContext.getSystemService(Context.ACCOUNT_SERVICE);
        AccountManager am = (AccountManager) this.getSystemService(ACCOUNT_SERVICE);
        Account mAccount=new Account(name,type);

        //Question 1.: What is the <Bundle> and what does it do?
        //AccountManagerCallback<Bundle> bundleAccountManagerCallback = ;
        //AccountManagerFuture<Bundle> accountManagerFuture = am.removeAccount(mAccount, null, AccountManagerCallback, null);
        //am.removeAccount(mAccount, null, AccountManagerCallback, null);
        //final AccountManagerFuture<Bundle> bundleAccountManagerFuture = am.removeAccount(mAccount, null, null, null);

        // Attempt 7.:
        am.removeAccount(mAccount, null, null, null);

        //atempt 8.:
        //        am.removeAccount(mAccount,new AccountManagerCallback<Boolean>(){
//                    public void run(    AccountManagerFuture<Boolean> future){
//                        boolean failed=true;
//                        try {
//                            if (future.getResult() == true) {
//                                failed=false;
//                            }
//                        }
//                        catch (      OperationCanceledException e) {
//                        }
//                        catch (      IOException e) {
//                        }
//                        catch (      AuthenticatorException e) {
//                        }
//                    }
//                }
//                ,null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







//    @Override
//    public Bundle getAccountRemovalAllowed(
//            AccountAuthenticatorResponse response, Account account)
//            throws NetworkErrorException {
//        final Bundle result = new Bundle();
//
//        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, true);
//
//        return result;
//    }
}