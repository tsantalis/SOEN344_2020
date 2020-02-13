/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package template;

import java.io.IOException;
import java.io.Reader;
import java.util.Vector;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.filters.ChainableReader;
import org.apache.tools.ant.types.Parameter;

/**
 * Filter which includes only those lines that contain all the user-specified
 * strings.
 *
 * Example:
 *
 * <pre>&lt;linecontains&gt;
 *   &lt;contains value=&quot;foo&quot;&gt;
 *   &lt;contains value=&quot;bar&quot;&gt;
 * &lt;/linecontains&gt;</pre>
 *
 * Or:
 *
 * <pre>&lt;filterreader classname=&quot;org.apache.tools.ant.filters.LineContains&quot;&gt;
 *    &lt;param type=&quot;contains&quot; value=&quot;foo&quot;/&gt;
 *    &lt;param type=&quot;contains&quot; value=&quot;bar&quot;/&gt;
 * &lt;/filterreader&gt;</pre>
 *
 * This will include only those lines that contain <code>foo</code> and
 * <code>bar</code>.
 *
 */
public final class LineContains
    extends BaseParamFilterReader
    implements ChainableReader {
    /** Parameter name for the words to filter on. */
    private static final String CONTAINS_KEY = "contains";

    /** Parameter name for the words to filter on. */
    private static final String NEGATE_KEY = "negate";

    /** Vector that holds the strings that input lines must contain. */
    private Vector<String> contains = new Vector<String>();

    /**
     * Constructor for "dummy" instances.
     *
     * @see BaseFilterReader#BaseFilterReader()
     */
    public LineContains() {
        super();
    }

    /**
     * Creates a new filtered reader.
     *
     * @param in A Reader object providing the underlying stream.
     *           Must not be <code>null</code>.
     */
    public LineContains(final Reader in) {
        super(in);
    }

    public int read() throws IOException {
    	return read((Parameter p) -> initialize(p), () -> match());
    }

    //@Override
	public boolean match() {
		boolean matches = true;
		for (int i = 0; matches && i < contains.size(); i++) {
		    String containsStr = (String) contains.elementAt(i);
		    matches = line.indexOf(containsStr) >= 0;
		}
		return matches;
	}

	//@Override
	public void initialize(Parameter parameter) {
		if (CONTAINS_KEY.equals(parameter.getType())) {
		    contains.addElement(parameter.getValue());
		} else if (NEGATE_KEY.equals(parameter.getType())) {
		    setNegate(Project.toBoolean(parameter.getValue()));
		}
	}

    /**
     * Adds a <code>contains</code> element.
     *
     * @param contains The <code>contains</code> element to add.
     *                 Must not be <code>null</code>.
     */
    public void addConfiguredContains(final Contains contains) {
        this.contains.addElement(contains.getValue());
    }

    /**
     * Sets the vector of words which must be contained within a line read
     * from the original stream in order for it to match this filter.
     *
     * @param contains A vector of words which must be contained within a line
     * in order for it to match in this filter. Must not be <code>null</code>.
     */
    private void setContains(final Vector<String> contains) {
        this.contains = contains;
    }

    /**
     * Returns the vector of words which must be contained within a line read
     * from the original stream in order for it to match this filter.
     *
     * @return the vector of words which must be contained within a line read
     * from the original stream in order for it to match this filter. The
     * returned object is "live" - in other words, changes made to the
     * returned object are mirrored in the filter.
     */
    private Vector<String> getContains() {
        return contains;
    }

    /**
     * Creates a new LineContains using the passed in
     * Reader for instantiation.
     *
     * @param rdr A Reader object providing the underlying stream.
     *            Must not be <code>null</code>.
     *
     * @return a new filter based on this configuration, but filtering
     *         the specified reader
     */
    public Reader chain(final Reader rdr) {
        LineContains newFilter = new LineContains(rdr);
        newFilter.setContains(getContains());
        newFilter.setNegate(isNegated());
        return newFilter;
    }

    /**
     * Holds a contains element
     */
    public static class Contains {

        /** User defined contains string */
        private String value;

        /**
         * Sets the contains string
         *
         * @param contains The contains string to set.
         *                 Must not be <code>null</code>.
         */
        public final void setValue(String contains) {
            value = contains;
        }

        /**
         * Returns the contains string.
         *
         * @return the contains string for this element
         */
        public final String getValue() {
            return value;
        }
    }
}
