/*
 * Copyright 2020-2021 Equinix, Inc
 * Copyright 2014-2021 The Billing Project, LLC
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.killbill.billing.usage;

import java.util.Objects;
import java.util.UUID;

import javax.annotation.Nullable;

import org.joda.time.LocalDate;
import org.killbill.billing.callcontext.DefaultTenantContext;
import org.killbill.billing.invoice.api.DryRunInfo;
import org.killbill.billing.invoice.api.DryRunType;
import org.killbill.billing.util.callcontext.TenantContext;

public class DryRunTenantContext extends DefaultTenantContext {

    private final DryRunType dryRunType;
    private final LocalDate targetDate;

    public DryRunTenantContext(@Nullable final DryRunInfo dryRunInfo, @Nullable final UUID accountId, @Nullable final UUID tenantId) {
        super(accountId, tenantId);
        this.dryRunType = dryRunInfo != null ? dryRunInfo.getDryRunType() : null;
        this.targetDate = dryRunInfo != null ? dryRunInfo.getInputTargetDate() : null;
    }

    public DryRunTenantContext(@Nullable final DryRunInfo dryRunInfo, final TenantContext tenantContext) {
        this(dryRunInfo, tenantContext.getAccountId(), tenantContext.getTenantId());
    }

    public DryRunType getDryRunType() {
        return dryRunType;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final DryRunTenantContext that = (DryRunTenantContext) o;
        return dryRunType == that.dryRunType && Objects.equals(targetDate, that.targetDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dryRunType, targetDate);
    }
}
