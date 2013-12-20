require 'test_helper'

class PagesControllerTest < ActionController::TestCase
  test "should get index" do
    get :index
    assert_response :success
  end

  test "should get myBoxes" do
    get :myBoxes
    assert_response :success
  end

  test "should get templateBoxes" do
    get :templateBoxes
    assert_response :success
  end

  test "should get settings" do
    get :settings
    assert_response :success
  end

end
