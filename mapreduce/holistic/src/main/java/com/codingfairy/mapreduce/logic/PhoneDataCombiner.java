package com.codingfairy.mapreduce.logic;

import com.codingfairy.to.Interval;
import com.codingfairy.vo.PhoneJson;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.htrace.fasterxml.jackson.databind.util.LinkedNode;

import java.util.*;

/**
 * Created by darxan on 2017/5/16.
 */
public class PhoneDataCombiner {

    private long time;

    public PhoneDataCombiner(long time) {
        this.time = time;
    }

    public PhoneDataCombiner() {
        this(0);
    }

    private Comparator<PhoneJson> phoneJsonComparator =
            new Comparator<PhoneJson>() {
                public int compare(PhoneJson o1, PhoneJson o2) {
                    long x = o1.getTime();
                    long y = o2.getTime();
                    return (x < y) ? -1 : ((x == y) ? 0 : 1);
                }
    };

    public List<PhoneJson> getPhonesData(Iterable<PhoneJson> values) {

        List<PhoneJson> phoneList = new ArrayList<PhoneJson>();
        for (PhoneJson tempObject: values) {
            phoneList.add(tempObject);
        }
        if (phoneList.size()==0) {
            return null;
        }
        Collections.sort(phoneList, phoneJsonComparator);
        return phoneList;
    }


    public List<PhoneJson> mergePhonesData(Iterable<ObjectWritable> objectWritableIterable) {

        int count = 0;
        PriorityQueue<LinkedNode<PhoneJson>> queue
                = new PriorityQueue<LinkedNode<PhoneJson>>(5, new Comparator<LinkedNode<PhoneJson>>() {
            public int compare(LinkedNode<PhoneJson> o1, LinkedNode<PhoneJson> o2) {
                return phoneJsonComparator.compare(o1.value(), o2.value());
            }
        });
        for (ObjectWritable objectWritable: objectWritableIterable) {
            count++;
            LinkedNode<PhoneJson> node = toLinkedList((List<PhoneJson>) objectWritable.get());
            queue.add(node);
        }
        List<PhoneJson> result = new ArrayList<PhoneJson>();
        while (!queue.isEmpty()) {

            result.add(queue.peek().value());
            if (queue.peek().next()!=null) {
                queue.add(queue.poll().next());
            }

        }
        return result;
    }


    private LinkedNode<PhoneJson> toLinkedList(List<PhoneJson> phoneJsons) {
        if (phoneJsons.size()==0)
            return null;
        LinkedNode<PhoneJson> header = new LinkedNode<PhoneJson>(phoneJsons.get(0), null);
        LinkedNode<PhoneJson> current = header;

        for (int i=1; i<phoneJsons.size(); i++) {
            LinkedNode<PhoneJson> next = new LinkedNode<PhoneJson>(phoneJsons.get(i), null);
            current.linkNext(next);
            current = next;
        }
        return header;
    }

}
