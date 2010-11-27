/*
 * Copyright 2010 the original author or authors.
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
package org.codenarc.analyzer

import org.codenarc.ruleset.RuleSet
import org.codenarc.results.Results
import org.codenarc.source.SourceString
import org.codenarc.results.VirtualResults

/**
 * Analyzes Strings.
 *
 * @author Hamlet D'Arcy
 */
class StringSourceAnalyzer implements SourceAnalyzer {
    SourceString source

    def StringSourceAnalyzer(source) {
        this.source = new SourceString(source)
    }

    def Results analyze(RuleSet ruleSet) {
        def allViolations = []
        ruleSet.rules.each { rule ->
            def violations = rule.applyTo(source)
            allViolations.addAll(violations)
        }
        new VirtualResults(allViolations)
    }

    def List getSourceDirectories() {
        []
    }
}