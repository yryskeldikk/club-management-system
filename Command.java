public interface Command {
    public void execute(String cmdParts[]) throws ExItemIdInUse, ExMemberIdInUse, ExMemberNotFound, ExItemNotAvailable, 
                                                    ExItemNotFound, ExLoanQuotaExceeded, ExItemNotBorrowedByMember,
                                                    ExRequestQuotaExceeded, ExSameMemberRequested, ExBorrowedBySameMember,
                                                    ExItemCurrentlyAvailable, ExRequestRecordNotFound, ExInsufficientCommandArg;
}
