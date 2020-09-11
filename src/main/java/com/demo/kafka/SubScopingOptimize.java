package com.demo.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubScopingOptimize {
	public static void main(String[] args) {

		String[] topics = new String[] { "ITERGO_D_E_Topic", "ITERGO_D_F_Topic", "ITERGO_D_A4_Topic",
				"ITERGO_D_A5_Topic", "ITERGO_D_A1_T1_Topic", "ITERGO_D_A1_T2_Topic", "ITERGO_D_A2_T1_Topic",
				"ITERGO_D_A2_T2_Topic", "ITERGO_D_A3_T1_Topic", "ITERGO_D_A3_T2_Topic", "ITERGO_D_B4_Topic",
				"ITERGO_D_B5_Topic", "ITERGO_D_B1_T1_Topic", "ITERGO_D_B1_T2_Topic", "ITERGO_D_B2_T1_Topic",
				"ITERGO_D_B2_T2_Topic", "ITERGO_D_B3_T1_Topic", "ITERGO_D_B3_T2_Topic", "ITERGO_D_C4_Topic",
				"ITERGO_D_C5_Topic", "ITERGO_D_C1_T1_Topic", "ITERGO_D_C1_T2_Topic", "ITERGO_D_C2_T1_Topic",
				"ITERGO_D_C2_T2_Topic", "ITERGO_D_C3_T1_Topic", "ITERGO_D_C3_T2_Topic", "ITERGO_D_C3_T3_Topic",
				"ITERGO_D_C3_T4_Topic", "ITERGO_D_C3_T1_X1_Topic01", "ITERGO_D_C3_T1_X1_Topic02" };

//		String[] subscopes = new String[] { "ITERGO_D", "ITERGO_D_A", "ITERGO_D_C", "ITERGO_D_A1", "ITERGO_D_A2",
//				"ITERGO_D_A3", "ITERGO_D_B1", "ITERGO_D_B2", "ITERGO_D_B3", "ITERGO_D_C1", "ITERGO_D_C2"
//
//		};

		String[] subscopes = new String[] { "ITERGO_D_A3", "ITERGO_D_C2", "ITERGO_D", "ITERGO_D_A", "ITERGO_D_C",
				"ITERGO_D_B3", "ITERGO_D_A1", "ITERGO_D_A2", "ITERGO_D_B1", "ITERGO_D_B2", "ITERGO_D_A1_T1",
				"ITERGO_D_A1_T2", "ITERGO_D_B1_T1", "ITERGO_D_B1_T2", "ITERGO_D_C1_T1", "ITERGO_D_C1_T2", "ITERGO_D_C1",

		};

		List<String> topicList = new ArrayList<>();
		topicList.addAll(Arrays.asList(topics));

		List<String> subscopeList = new ArrayList<>();
		subscopeList.addAll(Arrays.asList(subscopes));

		System.out.println("~~~ sort subscopes in ascending order ~~~");

		// sorting the subscope list in ascending order
		Collections.sort(subscopeList);

		Map<String, List<String>> subscopeTopicListMap = new HashMap<>();
		Map<String, List<String>> subscopeMap = new HashMap<>();

		for (String scope : subscopeList) {
			List<String> data = subscopeList.stream().filter(topic -> topic.startsWith(scope) && !topic.equals(scope))
					.collect(Collectors.toList());
			subscopeMap.put(scope, data);

		}

		System.out.println("~~~ Datastructure for subscope ~~~ ");
		subscopeMap.keySet().forEach(key -> System.out.println(key + "  " + subscopeMap.get(key)));

		String ikepnamespace = "ITERGO_D";
		System.out.println("~~~ Datastructure optimization ~~~ ");
		subscopeMap.keySet().forEach(key -> {
			if (!ikepnamespace.equals(key)) {
				if (subscopeMap.get(key).size() > 0)
					subscopeMap.get(ikepnamespace).removeAll(subscopeMap.get(key));
			}
		});
		subscopeMap.keySet().forEach(key -> System.out.println(key + "  " + subscopeMap.get(key)));

		List<String> ikepNameSpaceTopicList = topicList.stream().filter(topic -> topic.startsWith(ikepnamespace))
				.collect(Collectors.toList());

		subscopeList.sort((s1, s2) -> Math.abs(s2.length()) - Math.abs(s1.length()));

		// filter topics
		for (String key : subscopeList) {
			List<String> dataStructSubscopeList = subscopeMap.get(key);

			List<String> topicsStartScope = ikepNameSpaceTopicList.stream().filter(topic -> topic.startsWith(key))
					.collect(Collectors.toList());

			for (String scope : dataStructSubscopeList) {
				List<String> filteredTopics = ikepNameSpaceTopicList.stream().filter(topic -> topic.startsWith(scope))
						.collect(Collectors.toList());
				topicsStartScope.removeAll(filteredTopics);
			}

			subscopeTopicListMap.put(key, topicsStartScope);
			ikepNameSpaceTopicList.removeAll(topicsStartScope);
		}

		System.out.println("~~~~~~~~~~~Printing topics ~~~~~~~~~~~~~~~~~~~~~");
		subscopeTopicListMap.keySet().forEach(key -> System.out.println(key + "  " + subscopeTopicListMap.get(key)));

//		// add the topics to map with matching scope
//		for (String subscope : subscopeList) {
//			System.out.println("processing " + subscope);
//			List<String> topicsStartScope = topicList.stream().filter(topic -> topic.startsWith(subscope))
//					.collect(Collectors.toList());
//			subscopeTopicListMap.put(subscope, topicsStartScope);
//		}
//
//		System.out.println();
//		System.out.println(
//				"********Iteration 1 (contains duplicate topics in ikepnamespace scope & other scope )********");
//
//		for (String key : subscopeList) {
//			System.out.println(key);
//			System.out.println(subscopeTopicListMap.get(key).toString());
//			System.out.println();
//		}
//
//		Object[] subscopelistArray = subscopeList.toArray();
//
//		if (subscopeList.size() > 1) {
//			for (int i = 1; i < subscopelistArray.length; i++) {
//				List<String> list = subscopeTopicListMap.get(subscopelistArray[i]);
//
//				for (int j = 0; j < i; j++) {
//					// topics removed
//					Object subscope = subscopelistArray[j];
//					List<String> topicsListFromMap = subscopeTopicListMap.get(subscope);
//
//					if (subscope.toString().startsWith(subscopelistArray[i].toString()))
//						list.removeAll(topicsListFromMap);
//				}
//				subscopeTopicListMap.put(subscopelistArray[i].toString(), list);
//			}
//		}
//
//		System.out.println("********Iteration 2 (duplicates removed from ikepnamescope )********");
//		System.out.println();
//
//		for (String key : subscopeList) {
//			System.out.println(key);
//			System.out.println(subscopeTopicListMap.get(key).toString());
//			System.out.println();
//		}

	}
}
