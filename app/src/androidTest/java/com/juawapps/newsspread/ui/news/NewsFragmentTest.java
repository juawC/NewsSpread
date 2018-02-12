package com.juawapps.newsspread.ui.news;

import android.arch.lifecycle.MutableLiveData;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.juawapps.newsspread.R;
import com.juawapps.newsspread.TestApp;
import com.juawapps.newsspread.categories.NewsCategory;
import com.juawapps.newsspread.data.ArticlesRepository;
import com.juawapps.newsspread.data.Resource;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.test.SingleFragmentActivity;
import com.juawapps.newsspread.util.ObjectCreatorsUtil;
import com.juawapps.newsspread.utils.ui.FormatUtils;
import com.juawapps.newsspread.web.CustomTabHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;


/**
 * Fragment news test.
 */
@RunWith(AndroidJUnit4.class)
public class NewsFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> mActivityRule =
            new ActivityTestRule<>(SingleFragmentActivity.class, true, true);

    private MutableLiveData<Resource<List<Article>>> mArticlesList;
    private final ArticlesRepository mArticlesRepository = TestApp.get().getArticlesRepository();
    private final CustomTabHelper mCustomTabHelper = TestApp.get().getCustomTabHelper();

    @Before
    public void init() throws Throwable {
        String categoryKey = "category-key";

        reset(mArticlesRepository);
        mArticlesList = new MutableLiveData<>();
        when(mArticlesRepository.getArticles(categoryKey)).thenReturn(mArticlesList);

        mActivityRule.getActivity().replaceFragment(NewsFragment.Companion.newInstance(
                new NewsCategory(categoryKey, R.string.general_label)));
    }

    @Test
    public void loading() {
        mArticlesList.postValue(Resource.loading(null));
        onView(withId(R.id.spin_kit)).check(matches(isDisplayed()));
        onView(withId(R.id.error_retry_button)).check(matches(not(isDisplayed())));
    }


    @Test
    public void error() throws InterruptedException {
        Article article = ObjectCreatorsUtil.createArticle();
        List<Article> articleList = Collections.singletonList(article);

        mArticlesList.postValue(Resource.error("error", null));

        onView(withId(R.id.spin_kit)).check(matches(not(isDisplayed())));
        verify(mArticlesRepository).getArticles(eq("category-key"));
        onView(withId(R.id.error_text_title)).check(matches(isDisplayed()));
        onView(withId(R.id.error_retry_button)).check(matches(isDisplayed()));

        onView(withId(R.id.error_retry_button)).perform(click());
        mArticlesList.postValue(Resource.success(articleList));
        onView(withId(R.id.news_item_title)).check(matches(withText(article.getTitle())));
    }

    @Test
    public void loadedArticle() {
        Article article = ObjectCreatorsUtil.createArticle();
        List<Article> articleList = Collections.singletonList(article);
        mArticlesList.postValue(Resource.success(articleList));
        onView(withId(R.id.news_item_title)).check(matches(withText(article.getTitle())));
        onView(withId(R.id.news_item_subtitle)).check(matches(withText(article.getDescription())));
        onView(withId(R.id.news_item_source)).check(matches(withText(article.getSource().getName())));
        onView(withId(R.id.news_item_age)).check(
                matches(withText(FormatUtils.dateToAge(
                        mActivityRule.getActivity(), article.getPublishedAt()))));
        onView(withId(R.id.spin_kit)).check(matches(not(isDisplayed())));
    }

    @Test
    public void clickArticle() {
        Article article = ObjectCreatorsUtil.createArticle();
        List<Article> articleList = Collections.singletonList(article);
        mArticlesList.postValue(Resource.success(articleList));
        onView(withChild(withText(article.getTitle()))).perform(click());
        verify(mCustomTabHelper).openNewTab(eq(article.getUrl()), anyObject());
    }

    @Test
    public void nullArticleList() {
        mArticlesList.postValue(null);
        onView(withId(R.id.list)).check(matches(hasChildCount(0)));
    }
}
