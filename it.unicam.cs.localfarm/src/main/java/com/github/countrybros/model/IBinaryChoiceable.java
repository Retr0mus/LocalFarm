package com.github.countrybros.model;

/**
 * Interface to identify objects that can be accepted or denied
 */
@Deprecated
public interface IBinaryChoiceable {

    /**
     * Action to perform if it is accepted
     */
    boolean onApproval();

    /**
     * Action to perform if it is denied
     */
    boolean onRevocation();
}
