package com.juawapps.newsspread.ui.news;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.juawapps.newsspread.R;
import com.juawapps.newsspread.TestApp;
import com.juawapps.newsspread.categories.NewsCategoriesManager;
import com.juawapps.newsspread.categories.NewsCategory;
import com.juawapps.newsspread.data.ArticlesRepository;
import com.juawapps.newsspread.data.Resource;
import com.juawapps.newsspread.data.objects.Article;
import com.juawapps.newsspread.util.ObjectCreatorsUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * News activity test.
 */
@RunWith(AndroidJUnit4.class)
public class NewsActivityTest {
    @Rule
    public ActivityTestRule<NewsActivity> mActivityRule =
            new ActivityTestRule<>(NewsActivity.class, true, false);

    private final MutableLiveData<Resource<List<Article>>> mArticlesListFrag0 = new MutableLiveData<>();
    private final MutableLiveData<Resource<List<Article>>> mArticlesListFrag1 = new MutableLiveData<>();
    private final ArticlesRepository mArticlesRepository = TestApp.get().getArticlesRepository();

    private final List<NewsCategory> mNewsCategories = new NewsCategoriesManager().getCategories();

    @Before
    public void init() throws Throwable {
        reset(mArticlesRepository);
        when(mArticlesRepository.getArticles(mNewsCategories.get(0).getKey()))
                .thenReturn(mArticlesListFrag0);
        when(mArticlesRepository.getArticles(mNewsCategories.get(1).getKey()))
                .thenReturn(mArticlesListFrag1);
        mActivityRule.launchActivity(new Intent());
    }

    @Test
    public void loadedTabs() {
        onView(withText(mNewsCategories.get(0).getLabel())).check(matches(isDisplayed()));
        onView(withText(mNewsCategories.get(1).getLabel())).check(matches(isDisplayed()));
        onView(withText(mNewsCategories.get(2).getLabel())).check(matches(isDisplayed()));
    }

    @Test
    public void loadedFragment() {
        Article article = ObjectCreatorsUtil.createArticle();
        mArticlesListFrag0.postValue(Resource.success(Collections.singletonList(article)));
        onView(allOf(withId(R.id.news_item_title), withText(article.getTitle()))).check(matches(isDisplayed()));
    }

    @Test
    public void switchFragment() {
        Article articleFirst = ObjectCreatorsUtil.createArticle("First");
        mArticlesListFrag0.postValue(Resource.success(Collections.singletonList(articleFirst)));
        Article articleSecond = ObjectCreatorsUtil.createArticle("Second");
        mArticlesListFrag1.postValue(Resource.success(Collections.singletonList(articleSecond)));

        onView(allOf(withId(R.id.news_item_title), withText(articleFirst.getTitle()))).check(matches(isDisplayed()));

        onView(withText(mNewsCategories.get(1).getLabel())).perform(click());

        onView(allOf(withId(R.id.news_item_title), withText(articleSecond.getTitle()))).check(matches(isDisplayed()));
    }

}
