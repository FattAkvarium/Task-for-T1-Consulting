package ru.FattAkvarium.taskForT1Consulting.service;

import org.springframework.stereotype.Service;
import ru.FattAkvarium.taskForT1Consulting.model.IncomingString;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IncomingStringService implements StringService {

    private static final List<IncomingString> INCOMING_STRING_LIST = new ArrayList<>();

    @Override
    public void save(IncomingString incomingString) {
        INCOMING_STRING_LIST.add(incomingString);
    }

    public Map<String, Long> returnFillAndSortedMap() {
        Map<String, Long> mapOfSymbolsAndSymbolsCount = Arrays.stream(INCOMING_STRING_LIST
                        .get(INCOMING_STRING_LIST.size() - 1)
                        .getIncomingString()
                        .split(""))
                        .collect(Collectors.groupingBy(symbol -> symbol, Collectors.counting()));
        return sortedMap(mapOfSymbolsAndSymbolsCount);
    }

    public LinkedHashMap<String, Long> sortedMap(Map<String, Long> mapForSorting) {
        LinkedHashMap<String, Long> sortedMap = mapForSorting.entrySet()
                .stream()
                .sorted(Comparator.comparingLong(count -> count.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {throw new AssertionError();},
                        LinkedHashMap::new
                ));
        return sortedMap;
    }

}
