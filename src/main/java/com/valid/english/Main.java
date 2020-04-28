package com.valid.english;

import com.valid.english.factory.BeanFactory;
import com.valid.english.service.HotelService;
import com.valid.english.service.PersonService;
import com.valid.english.utils.RedissonManager;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * author: Jianzhang Mo
 * date: 2020-03-09
 * desc: code test
 */

public class Main {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String input = null;
        while(scanner.hasNext()) {
            input = scanner.next();
            break;
        }

        System.out.println("input = " + input);

        char[] chars = input.toCharArray();
        String priv = "";
        String next = "";
        StringBuffer piece = null;
        Set<String> pieceSet = new HashSet<String>();
        for(int i=0; i< chars.length; i++) {
            piece = new StringBuffer();
            priv = String.valueOf(chars[i]);
            piece.append(priv);
            for(int j=i+1; j < chars.length; j++) {
                next = String.valueOf(chars[j]);
                if(priv.equals(next)) {
                    piece.append(next);
                    if(j+1 == chars.length) {
                        pieceSet.add(piece.toString());
                        break;
                    }
                }else {
                    pieceSet.add(piece.toString());
                    i = j-1;
                    break;
                }
            }
        }

        for(String str: pieceSet) {

            System.out.println("str = "+ str);
        }
    }

}
