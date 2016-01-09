package com.ibm.tools.portfoliodb.dataloader;

import org.junit.Before;
import org.junit.Test;

import com.ibm.tools.portfoliodb.data.JSONDBSequencer;

public class JSONDBSequencerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetNextSequenceNumber() {
		System.out.println(JSONDBSequencer.getNextSequenceNumber("junit"));
		System.out.println(JSONDBSequencer.getNextSequenceNumber("junit"));
		System.out.println(JSONDBSequencer.getNextSequenceNumber("junit"));
		System.out.println(JSONDBSequencer.getNextSequenceNumber("junit"));
	}

}
