package com.yupi;

import com.yupi.cli.CommandExecutor;

/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/

public class Main {

    public static void main(String[] args) {
//       args = new String[]{"generate", "-l", "-a", "-o"};
//        args = new String[]{"config"};
//        args = new String[]{"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}