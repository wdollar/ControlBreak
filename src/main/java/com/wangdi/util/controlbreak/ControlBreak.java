package com.wangdi.util.controlbreak;

import java.util.Collection;
import java.util.Iterator;

/**
 * Control Break主处理
 * 
 * @author WangDi
 * @version 1.2
 * @date 2016/02/02
 */
public class ControlBreak {
	private static final int BREAK_LEVEL_NONE = 0;
	private static final int BREAK_LEVEL_16 = BREAK_LEVEL_NONE + 1;
	private static final int BREAK_LEVEL_15 = BREAK_LEVEL_16   + 1;
	private static final int BREAK_LEVEL_14 = BREAK_LEVEL_15   + 1;
	private static final int BREAK_LEVEL_13 = BREAK_LEVEL_14   + 1;
	private static final int BREAK_LEVEL_12 = BREAK_LEVEL_13   + 1;
	private static final int BREAK_LEVEL_11 = BREAK_LEVEL_12   + 1;
	private static final int BREAK_LEVEL_10 = BREAK_LEVEL_11   + 1;
	private static final int BREAK_LEVEL_9  = BREAK_LEVEL_10   + 1;
	private static final int BREAK_LEVEL_8  = BREAK_LEVEL_9    + 1;
	private static final int BREAK_LEVEL_7  = BREAK_LEVEL_8    + 1;
	private static final int BREAK_LEVEL_6  = BREAK_LEVEL_7    + 1;
	private static final int BREAK_LEVEL_5  = BREAK_LEVEL_6    + 1;
	private static final int BREAK_LEVEL_4  = BREAK_LEVEL_5    + 1;
	private static final int BREAK_LEVEL_3  = BREAK_LEVEL_4    + 1;
	private static final int BREAK_LEVEL_2  = BREAK_LEVEL_3    + 1;
	private static final int BREAK_LEVEL_1  = BREAK_LEVEL_2    + 1;
	private static final int BREAK_LEVEL_ALL = BREAK_LEVEL_1;

	/**
	 * Control Break主处理
	 * 
	 * @param breakHandler 提供ControlBreak所需的各种处理的IBreakHandler接口对象
	 * @param sortedCollection 已排序的集合类型
	 * @author WangDi
	 */
	public static <T> void doControlBreak(IBreakHandler<T> breakHandler, Collection<T> sortedCollection) {
		if (breakHandler == null) {
			throw new NullPointerException("breakHandler is null.");
		}
		if (sortedCollection == null) {
			throw new NullPointerException("sortedCollection is null.");
		}

		// Break层次
		int breakLevel = BREAK_LEVEL_NONE;

		// 前一条记录
		T prevRecord = null;

		// 当前记录
		T currRecord = null;

		// 无可用记录 标识(true:无可用记录 / false:有可用记录)
		boolean noAvailableRecord = true;

		Iterator<T> loopIterator = sortedCollection.iterator();
		
		// 最初的处理
		breakHandler.doPrepareBeforeAll();

		// 取出第1条可用记录（为了第1次的PrepareBeforeLevel处理）
		while (loopIterator.hasNext()) {
			// 取出一条记录
			currRecord = loopIterator.next();

			// SKIP判断
			if (breakHandler.isSkip(currRecord)) {
				// 清除已读取的记录，进入下次循环
				currRecord = null;
				continue;
			}

			// 有可用记录，终止循环
			noAvailableRecord = false;
			break;
		}

		// 无可用记录(全部记录都被跳过)的场合
		if (noAvailableRecord) {
			// 最终的处理
			breakHandler.doBreakAfterAll();
			return;
		}

		// 第1次PrepareBeforeLevel处理，针对所有层次
		doPrepareBeforeLevels(breakHandler, BREAK_LEVEL_ALL, currRecord);

		// 处理第1条记录
		doContinue(breakHandler, currRecord);

		// 第1条记录处理完成，当前记录变为前条记录，并清空当前记录的状态
		prevRecord = currRecord;
		currRecord = null;

		// 从第2条记录开始遍历所有记录
		while (loopIterator.hasNext()) {
			// 取出一条记录
			currRecord = loopIterator.next();

			// SKIP判断
			if (breakHandler.isSkip(currRecord)) {
				// 清除已读取的记录，进入下次循环
				currRecord = null;
				continue;
			}

			// 跟前一条记录作比较，判断Break层次
			breakLevel = checkBreakLevel(breakHandler, prevRecord, currRecord);

			// 发生Break 的场合
			if (breakLevel > BREAK_LEVEL_NONE) {
				// 根据前条记录做前一组数据的Break处理（可能为多层）
				doBreakAfterLevels(breakHandler, breakLevel, prevRecord);

				// 根据当前记录做本组数据的PrepareBefore处理（可能为多层）
				doPrepareBeforeLevels(breakHandler, breakLevel, currRecord);

				// PrepareBefore处理后清除Break层次（即视为无Break状态）
				breakLevel = BREAK_LEVEL_NONE;
			}

			// 处理当前记录
			doContinue(breakHandler, currRecord);

			// 当前记录变为前条记录，并清除当前记录
			prevRecord = currRecord;
			currRecord = null;
			continue;
		}

		// 最后一次BreakAfter处理（循环结束视作所有层次发生Break）
		doBreakAfterLevels(breakHandler, BREAK_LEVEL_ALL, prevRecord);

		// 最终的处理
		breakHandler.doBreakAfterAll();
	}

	private static <T> int checkBreakLevel(IBreakHandler<T> breakHandler, T currRecord, T nextRecord) {
		if (breakHandler.isKey1Break(currRecord, nextRecord))  return BREAK_LEVEL_1;
		if (breakHandler.isKey2Break(currRecord, nextRecord))  return BREAK_LEVEL_2;
		if (breakHandler.isKey3Break(currRecord, nextRecord))  return BREAK_LEVEL_3;
		if (breakHandler.isKey4Break(currRecord, nextRecord))  return BREAK_LEVEL_4;
		if (breakHandler.isKey5Break(currRecord, nextRecord))  return BREAK_LEVEL_5;
		if (breakHandler.isKey6Break(currRecord, nextRecord))  return BREAK_LEVEL_6;
		if (breakHandler.isKey7Break(currRecord, nextRecord))  return BREAK_LEVEL_7;
		if (breakHandler.isKey8Break(currRecord, nextRecord))  return BREAK_LEVEL_8;
		if (breakHandler.isKey9Break(currRecord, nextRecord))  return BREAK_LEVEL_9;
		if (breakHandler.isKey10Break(currRecord, nextRecord)) return BREAK_LEVEL_10;
		if (breakHandler.isKey11Break(currRecord, nextRecord)) return BREAK_LEVEL_11;
		if (breakHandler.isKey12Break(currRecord, nextRecord)) return BREAK_LEVEL_12;
		if (breakHandler.isKey13Break(currRecord, nextRecord)) return BREAK_LEVEL_13;
		if (breakHandler.isKey14Break(currRecord, nextRecord)) return BREAK_LEVEL_14;
		if (breakHandler.isKey15Break(currRecord, nextRecord)) return BREAK_LEVEL_15;
		if (breakHandler.isKey16Break(currRecord, nextRecord)) return BREAK_LEVEL_16;
		return BREAK_LEVEL_NONE;
	}

	private static <T> void doContinue(IBreakHandler<T> breakHandler, T currRec) {
		breakHandler.doContinue(currRec);
	}

	private static <T> void doPrepareBeforeLevels(IBreakHandler<T> breakHandler, int breakLevel, T currRec) {
		switch (breakLevel) {
		case BREAK_LEVEL_1:  breakHandler.doPrepareBeforeLevel1(currRec);
		case BREAK_LEVEL_2:  breakHandler.doPrepareBeforeLevel2(currRec);
		case BREAK_LEVEL_3:  breakHandler.doPrepareBeforeLevel3(currRec);
		case BREAK_LEVEL_4:  breakHandler.doPrepareBeforeLevel4(currRec);
		case BREAK_LEVEL_5:  breakHandler.doPrepareBeforeLevel5(currRec);
		case BREAK_LEVEL_6:  breakHandler.doPrepareBeforeLevel6(currRec);
		case BREAK_LEVEL_7:  breakHandler.doPrepareBeforeLevel7(currRec);
		case BREAK_LEVEL_8:  breakHandler.doPrepareBeforeLevel8(currRec);
		case BREAK_LEVEL_9:  breakHandler.doPrepareBeforeLevel9(currRec);
		case BREAK_LEVEL_10: breakHandler.doPrepareBeforeLevel10(currRec);
		case BREAK_LEVEL_11: breakHandler.doPrepareBeforeLevel11(currRec);
		case BREAK_LEVEL_12: breakHandler.doPrepareBeforeLevel12(currRec);
		case BREAK_LEVEL_13: breakHandler.doPrepareBeforeLevel13(currRec);
		case BREAK_LEVEL_14: breakHandler.doPrepareBeforeLevel14(currRec);
		case BREAK_LEVEL_15: breakHandler.doPrepareBeforeLevel15(currRec);
		case BREAK_LEVEL_16: breakHandler.doPrepareBeforeLevel16(currRec);
			break;
		default:
			break;
		}
	}

	private static <T> void doBreakAfterLevels(IBreakHandler<T> breakHandler, int breakLevel, T currRec) {
		LEVEL_SWITCH: {
			breakHandler.doBreakAfterLevel16(currRec);
			if (breakLevel == BREAK_LEVEL_16) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel15(currRec);
			if (breakLevel == BREAK_LEVEL_15) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel14(currRec);
			if (breakLevel == BREAK_LEVEL_14) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel13(currRec);
			if (breakLevel == BREAK_LEVEL_13) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel12(currRec);
			if (breakLevel == BREAK_LEVEL_12) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel11(currRec);
			if (breakLevel == BREAK_LEVEL_11) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel10(currRec);
			if (breakLevel == BREAK_LEVEL_10) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel9(currRec);
			if (breakLevel == BREAK_LEVEL_9) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel8(currRec);
			if (breakLevel == BREAK_LEVEL_8) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel7(currRec);
			if (breakLevel == BREAK_LEVEL_7) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel6(currRec);
			if (breakLevel == BREAK_LEVEL_6) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel5(currRec);
			if (breakLevel == BREAK_LEVEL_5) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel4(currRec);
			if (breakLevel == BREAK_LEVEL_4) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel3(currRec);
			if (breakLevel == BREAK_LEVEL_3) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel2(currRec);
			if (breakLevel == BREAK_LEVEL_2) break LEVEL_SWITCH;

			breakHandler.doBreakAfterLevel1(currRec);
			if (breakLevel == BREAK_LEVEL_1) break LEVEL_SWITCH;
		}
	}
}
