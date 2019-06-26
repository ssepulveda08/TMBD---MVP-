package com.example.tmdb.ListMovie;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdb.Data.Model.MovieDto;
import com.example.tmdb.ListMovie.ui.list.AdapterListMovie;
import com.example.tmdb.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListMovieFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListMovieFragment extends Fragment implements ListMovieView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CATEGORY = "param1";
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};

    // TODO: Rename and change types of parameters
    private int mcategory;

    private OnFragmentInteractionListener mListener;

    public ListMovieFragment() {
        // Required empty public constructor
    }

    //view
    private RecyclerView recycler;
    private TextView text_title;
    private AdapterListMovie  adapter;
    private LinearLayout linear_not;

    //presenter
    private ListMoviePresenter presenter;

    // TODO: Rename and change types and number of parameters
    public static ListMovieFragment newInstance(int param1 ) {
        ListMovieFragment fragment = new ListMovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CATEGORY, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mcategory = getArguments().getInt(ARG_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        presenter = new ListMoviePresenter( this, new ListMovieInteractor(getContext()));
        recycler = view.findViewById(R.id.recyclerview_movie);
        linear_not = view.findViewById(R.id.Linear_no_fount);
        presenter.onResume(mcategory);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setItems(ArrayList<MovieDto> items) {
        if (items.size()>0){
            recycler.setVisibility(View.VISIBLE);
            linear_not.setVisibility(View.GONE);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            recycler.setHasFixedSize(true);
            recycler.setLayoutManager(mLayoutManager);
            recycler.setItemAnimator(new DefaultItemAnimator());
            adapter = new AdapterListMovie(items, getActivity());
            recycler.setAdapter(adapter);
        }else{
            recycler.setVisibility(View.GONE);
            linear_not.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchItem = menu.findItem(R.id.search);
        //buscardor
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(), "submit", Toast.LENGTH_SHORT).show();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                 Toast.makeText(getContext(), "change", Toast.LENGTH_SHORT).show();
                    if (newText.length()>0){
                        adapter.getFilter().filter(newText);
                    }
                return true;
            }
        });
        searchView.setOnCloseListener(() -> {
            adapter.getFilter().filter("");
            return true;
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
