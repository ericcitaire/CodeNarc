/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codenarc.rule.basic

import org.codenarc.rule.AbstractRuleTestCase
import org.codenarc.rule.Rule

/**
 * Tests for DuplicateMapKeyRule
 *
 * @author 'Łukasz Indykiewicz'
 * @version $Revision: 329 $ - $Date: 2010-04-29 04:20:25 +0200 (Thu, 29 Apr 2010) $
 */
class DuplicateMapKeyRuleTest extends AbstractRuleTestCase {

    void testRuleProperties() {
        assert rule.priority == 2
        assert rule.name == 'DuplicateMapKey'
    }

    void testSuccessScenario() {
        final SOURCE = '''
        	  def var4 = [a:1, b:1, c:1]
              def var5 = [1:1, 2:1, 3:1]
              def var6 = ["a":1, "b":1, "c":1]
        '''
        assertNoViolations(SOURCE)
    }

    void testDuplicateKey() {
        final SOURCE = '''
              def var1 = [a:1,
                    a:2,
                    b:3]
        '''
        assertSingleViolation(SOURCE, 3, 'a:2,', "Key 'a' is duplicated.")
    }

    void testMultipleDuplicateKeys() {
        final SOURCE = '''
              def var1 = [a:1,
                    a:2,
                    a:4,
                    b:3]
        '''
        assertTwoViolations(SOURCE,
                3, 'a:2,', "Key 'a' is duplicated.",
                4, 'a:4,', "Key 'a' is duplicated.")
    }

    void testDuplicateIntegerKey() {
        final SOURCE = '''
              def var2 = [1:1, 1:2, 2:3]
        '''
        assertSingleViolation(SOURCE, 2, 'def var2 = [1:1, 1:2, 2:3]', "Key '1' is duplicated.")
    }

    void testDuplicateStringKey() {
        final SOURCE = '''
              def var3 = ["a":1, "a":2, "b":3]
        '''
        assertSingleViolation SOURCE, 2, 'def var3 = ["a":1, "a":2, "b":3]', "Key 'a' is duplicated."
    }

    protected Rule createRule() {
        new DuplicateMapKeyRule()
    }
}