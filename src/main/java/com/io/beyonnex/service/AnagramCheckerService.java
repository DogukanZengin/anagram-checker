package com.io.beyonnex.service;

import com.io.beyonnex.model.InputDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AnagramCheckerService {

    /**
     * Takes two strings and finds out if they are anagrams.
     * Algorithm Explanation
     *  - ASCII chars a-z corresponds to numbers 97-122 in the character table.
     *   To have an anagram between two text input, every character should occur exactly the same number of times in both texts.
     *   By using this information and the number values of ASCII characters, we know that sum of character values in both texts should be equal
     *   otherwise, anagram contract is broken.
     * <p>
     *   Time complexity is O(n), "n" being the length of the both strings
     * </p>
     */
    public boolean isAnagram(InputDto dto){

        var first = dto.first();
        var second = dto.second();

        //Both strings should be neither empty nor null
        if(StringUtils.isEmpty(first) || StringUtils.isEmpty(second)){
            throw new IllegalArgumentException("Inputs can not be empty or null");
        }

        // Both strings should only contain unicode letters. @See README - Assumptions section
        if(!StringUtils.isAlpha(first) || !StringUtils.isAlpha(second)){
            throw new IllegalArgumentException("Inputs should contain only letters");
        }

        // Text lengths should be equal, otherwise anagram contract is broken
        if(first.length() != second.length()){
            return false;
        }

        // Make the strings case-insensitive. @See README - Assumptions section
        first = first.toLowerCase();
        second = second.toLowerCase();

        int length = first.length();
        int sum = 0;

        for(int i = 0; i< length; i++){
            sum += first.charAt(i);
            sum -= second.charAt(i);
        }

        return sum == 0;
    }
}
