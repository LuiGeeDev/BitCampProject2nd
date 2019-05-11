$(".recomment-option").click(function() {
  const recommentDiv = $(this)
    .parent()
    .parent()
    .siblings(".recomment-input");
  recommentDiv.removeClass("unseen");
});

$(".article .comment-option").click(function() {
  $(".modal").css("display", "block");
  const receiver_id = $(".article .writer-id").text();
  $(".message-form").append(
    "<input type='hidden' value='" +
      receiver_id +
      "' name='receiver' class='receiver'>"
  );
});

$(".comment-sub .comment-option").click(function() {
  $(".modal").css("display", "block");
  const receiver_id = $(this)
    .parent()
    .parent()
    .children(".comment-main")
    .children(".comment-writer")
    .children(".comment-id")
    .text();
  $(".message-form").append(
    "<input type='hidden' value='" +
      receiver_id +
      "' name='receiver' class='receiver'>"
  );
});

$(".close-btn").click(function() {
  $(".receiver").remove();
  $(".modal").css("display", "none");
});